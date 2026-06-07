/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.clubbuceo;

import com.mycompany.clubbuceo.enums.Titulacion;
import com.mycompany.clubbuceo.enums.Organizacion;
import com.mycompany.clubbuceo.clases.Buceador;
import com.mycompany.clubbuceo.clases.BuceadorDAO;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Alejandro
 */

// Controlador del formulario de alta buceador
public class AltaBuceadorController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private DatePicker dpFechaNacimiento;
    @FXML
    private TextField txtEmail;
    @FXML
    private ComboBox<Titulacion> cmbTitulacion;
    @FXML
    private ComboBox<Organizacion> cmbOrganizacion;
    @FXML
    private ComboBox<String> cmbGrupoSanguineo;
    @FXML
    private DatePicker dpFechaReconocimiento;
    @FXML
    private TextField txtAlergias;
    @FXML
    private TextField txtCompaniaSeguro;
    @FXML
    private DatePicker dpFechaCaducidadSeguro;
    @FXML
    private TextField txtContEmergNombre;
    @FXML
    private TextField txtDni;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtInmersiones;
    @FXML
    private TextField txtContEmergTelf;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnVolver;
    @FXML
    private Label lbConfirmacion;
    @FXML
    private Label lbError;

    /**
     * Initializes the controller class.
     */
    
    // Rellena los ComboBox y establece los valores por defecto del formulario.
    // El grupo sanguíneo se carga manualmente porque los valores con '+'/'-' dan problemas en un enum
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //AÑADIMOS A LOS COMBOBOX LOS VALORES DE LOS ENUMS CORRESPONDIENTE EN EL CASO DEL GRUPO SANGUINEO INTRODUCIMOS DIRECTAMENTE LOS VALORES PORQUÉ DABA PROBLEMAS PONERLOS ASÍ EN UN ENUM
        cmbTitulacion.getItems().addAll(Titulacion.values());
        cmbOrganizacion.getItems().addAll(Organizacion.values());
        cmbGrupoSanguineo.getItems().addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");    
        
        cmbTitulacion.setValue(Titulacion.Otro);
        cmbOrganizacion.setValue(Organizacion.SSI);
        txtInmersiones.setText("0");
        cmbGrupoSanguineo.setValue("O+");
    }    

    // Recoge los datos del formulario, construye un Buceador y lo inserta en la BD.
    // Captura errores de validación (setters) y campos vacíos (NullPointerException).
    @FXML
    private void Guardar(ActionEvent event) {
        
        try{
        // Se construye el objeto Buceador con los datos del formulario.
        // La fecha de alta se asigna automáticamente al día actual
        Buceador b1 = new Buceador(
                Integer.parseInt(txtTelefono.getText()),
                Integer.parseInt(txtContEmergTelf.getText()),
                Integer.parseInt(txtInmersiones.getText()),
                txtDni.getText(),
                txtNombre.getText(),
                txtApellidos.getText(),
                txtEmail.getText(),
                txtCompaniaSeguro.getText(),
                txtContEmergNombre.getText(),
                cmbGrupoSanguineo.getValue(),
                txtAlergias.getText(),
                cmbTitulacion.getValue(),
                cmbOrganizacion.getValue(),
                dpFechaNacimiento.getValue(),
                LocalDate.now(),
                dpFechaReconocimiento.getValue(),
                dpFechaCaducidadSeguro.getValue()
        );
       
            // Se comprueba si ya existe un buceador con ese DNI antes de insertar
            if(BuceadorDAO.listarTodos().containsValue(b1)){
                
                lbError.setText("Error. Ya existe un buceador con ese DNI.");

            }else if(BuceadorDAO.insertar(b1)){
                
                // Inserción exitosa: se notifica al usuario y se actualiza el respaldo local
                lbError.setText(null);
                lbConfirmacion.setText("El buceador se ha dado de alta.");
                App.guardarDatos();
                
            }else{
                
                // La inserción devolvió false: error en la BD
                lbConfirmacion.setText(null);
                lbError.setText("ERROR! No se pudo dar de alta al buceador. Revisar OUTPUT para mas informacion.");
                
            }
            
        }catch(IllegalArgumentException ex){
            
            lbConfirmacion.setText(null);
            lbError.setText(ex.getMessage());
            
        }catch(NullPointerException ex){
            
            lbError.setText(ex.getMessage());
            
        }
    }

    // Navega de vuelta a la pantalla del menú principal
    @FXML
    private void Volver(ActionEvent event) throws IOException {
        App.setRoot("Menu");
    }



   
}
