/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.clubbuceo;

import com.mycompany.clubbuceo.clases.BuceadorDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Alejandro
 */

// Controlador de la vista de eliminar buceadores
public class EliminarBuceadorController implements Initializable {

    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnVolver;
    @FXML
    private TextField txtId;
    @FXML
    private TextArea taResultado;

    // Almacena el id del buceador encontrado para compartirlo entre Buscar y Eliminar
    private static Integer idBuscado;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
    }    

    // Busca en la BD el buceador con el id introducido y muestra su ficha.
    // Habilita el botón Eliminar solo si se encuentra; lo deshabilita si no existe.
    @FXML
    private void Buscar(ActionEvent event) {
        if(BuceadorDAO.listarTodos().containsKey(Integer.parseInt(txtId.getText()))){
            idBuscado = Integer.parseInt(txtId.getText());
            taResultado.setText(BuceadorDAO.listarTodos().get(idBuscado).toString());
            btnEliminar.setDisable(false);
        }else{
            taResultado.setText("No se ha encontrado ningun buceador con ese ID");
            btnEliminar.setDisable(true);
        }
    }

    // Elimina de la BD al buceador con el id buscado, actualiza el respaldo local
    // y deshabilita el botón Eliminar para evitar una doble eliminación.
    @FXML
    private void Eliminar(ActionEvent event) {
        if(BuceadorDAO.eliminar(idBuscado)){
            
            taResultado.setText("Se ha dado de baja al buceador indicado de forma correcta");
            App.guardarDatos();
            btnEliminar.setDisable(true);
            
        }else{
            
            taResultado.setText("Error! No se ha dado de baja al buceador"); 
            
        }
    }

    // Navega de vuelta a la pantalla del menú principal
    @FXML
    private void Volver(ActionEvent event) throws IOException {
        
        App.setRoot("Menu");
    
    }
    
}
