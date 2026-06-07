package com.mycompany.clubbuceo;

import com.mycompany.clubbuceo.clases.Buceador;
import com.mycompany.clubbuceo.clases.BuceadorDAO;
import com.mycompany.clubbuceo.clases.Conexion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.io.*;
import java.util.TreeMap;

/**
 * JavaFX App
 */

// Clase principal de la aplicación. Gestiona el arranque, la navegación entre vistas
// y el respaldo local de datos cuando no hay conexión a la BD.
public class App extends Application {

    // Mapa de respaldo usado cuando no hay conexión a la BD
    static TreeMap<Integer, Buceador> datosRespaldo = new TreeMap<Integer, Buceador>();
    
    private static Scene scene;

    // Abre la aplicación mostrando la vista del menú principal
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Menu"));
        stage.setScene(scene);
        stage.show();
    }

    // Cambia la vista actual por la indicada y reajusta la ventana a su contenido
    static void setRoot(String fxml) throws IOException {
        
        scene.setRoot(loadFXML(fxml));
        Stage stage = (Stage) scene.getWindow();
        stage.sizeToScene();
        stage.centerOnScreen();
        
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    // Punto de entrada. Comprueba la conexión a la BD antes de arrancar;
    // si no hay conexión, carga los datos del fichero local de respaldo
    public static void main(String[] args) {
        
        if(!hayConexion()){
            cargarDatos();   
        }
        
        launch();
    }

    // Lee el fichero binario 'buceadores.dat' y vuelca su contenido en datosRespaldo.
    // Se usa como fallback cuando la BD no está disponible.
    public static void cargarDatos(){
        try(ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("buceadores.dat"))){
            
            datosRespaldo = (TreeMap<Integer, Buceador>) entrada.readObject();
            
        }catch (FileNotFoundException ex) {
            
            System.out.println("No se han encontrado datos guardados localmente.");
            
        } catch (IOException | ClassNotFoundException ex) {
            
            System.out.println(ex.getMessage());
            
        }
    }
    
    // Serializa el contenido actual de la BD en el fichero 'buceadores.dat'.
    // Se llama tras cada operación de escritura para mantener el respaldo actualizado.
    public static void guardarDatos(){
        try(ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("buceadores.dat"))){
            
            salida.writeObject(BuceadorDAO.listarTodos());
        
        }catch (FileNotFoundException ex) {
            System.out.println("No se han encontrado datos guardados localmente.");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    // Intenta abrir una conexión con la BD. Devuelve true si tiene éxito, false si falla
    public static boolean hayConexion(){
        try (Connection con = Conexion.getConexion()) {

            return true;
            
        } catch (SQLException e) {
         
            return false;
            
        }
    }
}