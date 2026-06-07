/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.clubbuceo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Alejandro
 */

// Controlador del menú principal. Gestiona la navegación entre vistas
// y deshabilita las opciones de escritura si no hay conexión a la BD.
public class MenuController implements Initializable {

    @FXML
    private Button btnMostrar;
    @FXML
    private Button btnAnadir;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnCerrar;

    /**
     * Initializes the controller class.
     */
    
    // Si no hay conexión a la BD, deshabilita los botones que modifican datos
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(!App.hayConexion()){
            btnAnadir.setDisable(true);
            btnModificar.setDisable(true);
            btnEliminar.setDisable(true);
        }
    }    

    // Navega a la vista de mostrar buceadores
    @FXML
    private void MostrarBuceadores(ActionEvent event) throws IOException {
        App.setRoot("mostrarBuceadores");
    }

    // Navega a la vista de alta buceador
    @FXML
    private void AltaBuceador(ActionEvent event) throws IOException {
        App.setRoot("AltaBuceador");
    }

    // Navega a la vista de modificar buceador
    @FXML
    private void ModificarBuceador(ActionEvent event) throws IOException {
        App.setRoot("ModificarBuceador");
    }

    // Navega a la vista de eliminar
    @FXML
    private void EliminarBuceador(ActionEvent event) throws IOException {
        App.setRoot("EliminarBuceador");
    }

    // Guarda el respaldo local si hay conexión y cierra la aplicación
    @FXML
    private void Cerrar(ActionEvent event) {
        if(App.hayConexion()){
            App.guardarDatos();
        }
        System.exit(0);
    }
    
}
