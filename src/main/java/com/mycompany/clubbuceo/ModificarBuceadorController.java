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


// Controlador de la vista de modificar buceador
public class ModificarBuceadorController implements Initializable {

    @FXML
    private TextField txtDni;
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
    private Button btnBuscar;
    @FXML
    private Button btnVolver;
    @FXML
    private TextField txtBuscarId;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtInmersiones;
    @FXML
    private TextField txtContEmergTelf;
    @FXML
    private Button btnGuardar;
    @FXML
    private Label lbError;
    @FXML
    private Label lbConfirmacion;
    
    
    /**
     * Initializes the controller class.
     */
    
    // Almacena el id del buceador encontrado para compartirlo entre Buscar y Guardar
    private static int idBuscado;
   
    // Rellena los ComboBox con sus valores. El grupo sanguíneo se carga manualmente
    // porque los valores con '+'/'-' dan problemas en un enum
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //AÑADIMOS A LOS COMBOBOX LOS VALORES DE LOS ENUMS CORRESPONDIENTE EN EL CASO DEL GRUPO SANGUINEO INTRODUCIMOS DIRECTAMENTE LOS VALORES PORQUÉ DABA PROBLEMAS PONERLOS ASÍ EN UN ENUM
        cmbTitulacion.getItems().addAll(Titulacion.values());
        cmbOrganizacion.getItems().addAll(Organizacion.values());
        cmbGrupoSanguineo.getItems().addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
    }    

    // Navega de vuelta a la pantalla del menú principal
    @FXML
    private void Volver(ActionEvent event) throws IOException {
        App.setRoot("Menu");
    }

    // Busca el buceador con el id introducido y vuelca sus datos en el formulario para editarlos.
    // Si no existe, limpia el formulario y deshabilita el botón Guardar.
    @FXML
    private void Buscar(ActionEvent event) {

        idBuscado = Integer.parseInt(txtBuscarId.getText());
        lbConfirmacion.setText(null);

        if(BuceadorDAO.listarTodos().containsKey(idBuscado)){
            lbError.setText(null);
            
            // Rellena cada campo con los datos actuales del buceador para que el usuario los edite
            txtDni.setText(BuceadorDAO.listarTodos().get(idBuscado).getDni());
            txtNombre.setText(BuceadorDAO.listarTodos().get(idBuscado).getNombre());
            txtApellidos.setText(BuceadorDAO.listarTodos().get(idBuscado).getApellidos());
            dpFechaNacimiento.setValue(BuceadorDAO.listarTodos().get(idBuscado).getFechaNacimiento());
            txtEmail.setText(BuceadorDAO.listarTodos().get(idBuscado).getEmail());
            cmbTitulacion.setValue(BuceadorDAO.listarTodos().get(idBuscado).getTitulacion());
            cmbOrganizacion.setValue(BuceadorDAO.listarTodos().get(idBuscado).getOrganizacion());
            dpFechaReconocimiento.setValue(BuceadorDAO.listarTodos().get(idBuscado).getFechaReconocimiento());
            txtAlergias.setText(BuceadorDAO.listarTodos().get(idBuscado).getAlergias());
            txtCompaniaSeguro.setText(BuceadorDAO.listarTodos().get(idBuscado).getCompaniaSeguro());
            dpFechaCaducidadSeguro.setValue(BuceadorDAO.listarTodos().get(idBuscado).getFechaCaducidadSeguro());
            txtContEmergNombre.setText(BuceadorDAO.listarTodos().get(idBuscado).getContEmergNombre());
            txtTelefono.setText(String.valueOf(BuceadorDAO.listarTodos().get(idBuscado).getTelefono()));
            txtInmersiones.setText(String.valueOf(BuceadorDAO.listarTodos().get(idBuscado).getInmersiones()));
            txtContEmergTelf.setText(String.valueOf(BuceadorDAO.listarTodos().get(idBuscado).getContEmergTelf()));
            cmbGrupoSanguineo.setValue(BuceadorDAO.listarTodos().get(idBuscado).getGrupoSanguineo());
            
            btnGuardar.setDisable(false);
            
        }else{
            lbError.setText("Error. No se ha encontrado un buceador con ese ID.");
            
            // Limpia todos los campos si no se encuentra el buceador
            txtDni.clear();
            txtNombre.clear();
            txtApellidos.clear();
            dpFechaNacimiento.setValue(null);
            txtEmail.clear();
            cmbTitulacion.setValue(null);
            cmbOrganizacion.setValue(null);
            dpFechaReconocimiento.setValue(null);
            txtAlergias.clear();
            txtCompaniaSeguro.clear();
            dpFechaCaducidadSeguro.setValue(null);
            txtContEmergNombre.clear();
            txtTelefono.clear();
            txtInmersiones.clear();
            txtContEmergTelf.clear();
            cmbGrupoSanguineo.setValue(null);
            btnGuardar.setDisable(true);
        }
    }

    // Construye un Buceador con los datos editados del formulario y lo envía a la BD.
    // Conserva la fecha de alta original. Deshabilita el botón Guardar tras aplicar los cambios.
    @FXML
    private void Guardar(ActionEvent event) {
        // Crea un objeto buceador con los nuevos datos del formulario
        try{
            
            Buceador modificado = new Buceador(
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
                    BuceadorDAO.listarTodos().get(idBuscado).getFechaAlta(),
                    dpFechaReconocimiento.getValue(),
                    dpFechaCaducidadSeguro.getValue()
            );
        
        // Ejecuta el metodo modificar de BuceadorDAO con el idBuscado y el objeto "modificado" como parámetros
        if(BuceadorDAO.modificar(idBuscado, modificado)){
            
            // Modificación exitosa: se notifica al usuario y se actualiza el respaldo local
            lbError.setText(null);
            lbConfirmacion.setText("Se han realizado las modificaciones correctamente.");
            
            App.guardarDatos();
            btnGuardar.setDisable(true);
            
        }else{
            
            // La modificación devolvió false: error en la BD
            lbError.setText("ERROR. No se han aplicado los cambios. Consultar Output para mas informacion.");
            
        }
        
        // Captura errores de validación lanzados por los setters de Buceador
        }catch(IllegalArgumentException ex){
            lbError.setText(ex.getMessage());
        }
    }
    
}
