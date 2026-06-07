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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Alejandro
 */

// Controlador de la vista de mostrar buceadores
public class MostrarBuceadoresController implements Initializable {

    @FXML
    private Button btnVolver;
    @FXML
    private TableView<Buceador> tablaBuceadores;
    @FXML
    private TableColumn<Buceador, Integer> colId;
    @FXML
    private TableColumn<Buceador, String> colDni;
    @FXML
    private TableColumn<Buceador, String> colNombre;
    @FXML
    private TableColumn<Buceador, String> colApellidos;
    @FXML
    private TableColumn<Buceador, LocalDate> colFechaNacimiento;
    @FXML
    private TableColumn<Buceador, String> colEmail;
    @FXML
    private TableColumn<Buceador, Integer> colTelefono;
    @FXML
    private TableColumn<Buceador, Titulacion> colTitulacion;
    @FXML
    private TableColumn<Buceador, Organizacion> colOrganizacion;
    @FXML
    private TableColumn<Buceador, Integer> colInmersiones;
    @FXML
    private TableColumn<Buceador, String> colGrupoSanguineo;
    @FXML
    private TableColumn<Buceador, String> colAlergias;
    @FXML
    private TableColumn<Buceador, LocalDate> colReconocimiento;
    @FXML
    private TableColumn<Buceador, String> colCompaniaSeguro;
    @FXML
    private TableColumn<Buceador, LocalDate> colCaducidadSeguro;
    @FXML
    private TableColumn<Buceador, String> colContEmergNombre;
    @FXML
    private TableColumn<Buceador, Integer> colContEmergTelf;
    @FXML
    private TableColumn<Buceador, LocalDate> colFechaAlta;

    
    // Carga los datos en la tabla. Si hay conexión los obtiene de la BD;
    // si no, usa el respaldo local. Cada columna se enlaza con su getter de Buceador
    // mediante PropertyValueFactory para que la TableView muestre los valores automáticamente.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Buceador> data;
        
        // Fuente de datos: BD si hay conexión, respaldo local en caso contrario
        if(App.hayConexion()){
            data = FXCollections.observableArrayList(BuceadorDAO.listarTodos().values());
        }else{
            data = FXCollections.observableArrayList(App.datosRespaldo.values());
        }
        
        // Se enlaza cada columna con el getter correspondiente de Buceador
        colId.setCellValueFactory(new PropertyValueFactory("Id"));
        colDni.setCellValueFactory(new PropertyValueFactory("Dni"));
        colNombre.setCellValueFactory(new PropertyValueFactory("Nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory("Apellidos"));
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory("FechaNacimiento"));
        colEmail.setCellValueFactory(new PropertyValueFactory("Email"));
        colTelefono.setCellValueFactory(new PropertyValueFactory("Telefono"));
        colTitulacion.setCellValueFactory(new PropertyValueFactory("Titulacion"));
        colOrganizacion.setCellValueFactory(new PropertyValueFactory("Organizacion"));
        colInmersiones.setCellValueFactory(new PropertyValueFactory("Inmersiones"));
        colGrupoSanguineo.setCellValueFactory(new PropertyValueFactory("GrupoSanguineo"));
        colAlergias.setCellValueFactory(new PropertyValueFactory("Alergias"));
        colReconocimiento.setCellValueFactory(new PropertyValueFactory("FechaReconocimiento"));
        colCompaniaSeguro.setCellValueFactory(new PropertyValueFactory("CompaniaSeguro"));
        colCaducidadSeguro.setCellValueFactory(new PropertyValueFactory("FechaCaducidadSeguro"));
        colContEmergNombre.setCellValueFactory(new PropertyValueFactory("ContEmergNombre"));
        colContEmergTelf.setCellValueFactory(new PropertyValueFactory("ContEmergTelf"));
        colFechaAlta.setCellValueFactory(new PropertyValueFactory("FechaAlta"));
        
        //Se cargan los datos en la tabla
        tablaBuceadores.setItems(data);
    }    

    // Navega de vuelta a la pantalla del menú principal
    @FXML
    private void Volver(ActionEvent event) throws IOException {
        App.setRoot("Menu");
    }
    
}
