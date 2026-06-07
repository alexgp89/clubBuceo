/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubbuceo.clases;

import com.mycompany.clubbuceo.enums.Organizacion;
import com.mycompany.clubbuceo.enums.Titulacion;
import java.sql.*;
import java.util.TreeMap;

/**
 *
 * @author Alejandro
 */

// Clase DAO que centraliza todas las operaciones CRUD sobre la tabla 'buceadores'
public class BuceadorDAO {
    
    // Inserta un nuevo buceador en la BD. Devuelve true si se completó correctamente
    public static boolean insertar(Buceador b) {
    
    String sql ="INSERT INTO buceadores (dni, nombre, apellidos, email, telefono, fecha_nacimiento, fecha_alta, titulacion, organizacion, num_inmersiones, grupo_sanguineo, alergias, fecha_reconocimiento, compania_seguro, fecha_cad_seguro, cont_emerg_nombre, cont_emerg_telf) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            
            // Se mapean los atributos del buceador a cada '?' de la sentencia
            ps.setString(1,  b.getDni());
            ps.setString(2,  b.getNombre());
            ps.setString(3,  b.getApellidos());
            ps.setString(4,  b.getEmail());
            ps.setInt(5,     b.getTelefono());
            ps.setDate(6,    Date.valueOf(b.getFechaNacimiento()));
            ps.setDate(7,    Date.valueOf(b.getFechaAlta()));
            ps.setString(8,  b.getTitulacion().name());
            ps.setString(9,  b.getOrganizacion().name());
            ps.setInt(10,    b.getInmersiones());
            ps.setString(11, b.getGrupoSanguineo());
            ps.setString(12, b.getAlergias());
            ps.setDate(13,   Date.valueOf(b.getFechaReconocimiento()));
            ps.setString(14, b.getCompaniaSeguro());
            ps.setDate(15,   Date.valueOf(b.getFechaCaducidadSeguro()));
            ps.setString(16, b.getContEmergNombre());
            ps.setInt(17,    b.getContEmergTelf());

            ps.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
            return false;
        }
    }
    
    // Lee todos los buceadores de la BD y los devuelve en un TreeMap<id, Buceador>
    public static TreeMap<Integer, Buceador> listarTodos() {

        TreeMap<Integer, Buceador> mapa = new TreeMap<>();
        String sql = "SELECT * FROM buceadores";
        
        try (Connection con = Conexion.getConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            
            // Cada fila del ResultSet se convierte en un objeto Buceador y se añade al mapa
            while (rs.next()) {
                
                mapa.put(rs.getInt("id_buceador"), 
                        new Buceador(
                        rs.getInt("id_buceador"), 
                        rs.getInt("telefono"), 
                        rs.getInt("cont_emerg_telf"), 
                        rs.getInt("num_inmersiones"), 
                        rs.getString("dni"), 
                        rs.getString("nombre"), 
                        rs.getString("apellidos"), 
                        rs.getString("email"), 
                        rs.getString("compania_seguro"), 
                        rs.getString("cont_emerg_nombre"), 
                        rs.getString("grupo_sanguineo"), 
                        rs.getString("alergias"),
                        // Los enums se recuperan por nombre desde la BD
                        Titulacion.valueOf(rs.getString("titulacion")), 
                        Organizacion.valueOf(rs.getString("organizacion")), 
                        rs.getDate("fecha_nacimiento").toLocalDate(), 
                        rs.getDate("fecha_alta").toLocalDate(), 
                        rs.getDate("fecha_reconocimiento").toLocalDate(), 
                        rs.getDate("fecha_cad_seguro").toLocalDate()));
            }
            
        } catch (SQLException e){
            
            e.printStackTrace(); 
        }

        return mapa;
        
    }
    
    // Elimina de la BD al buceador con el id indicado. Devuelve true si se completó correctamente
    public static boolean eliminar(int idBuceador) {

        String sql = "DELETE FROM buceadores WHERE id_buceador = ?";
        
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idBuceador);
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e) { 
            
            e.printStackTrace();
            return false;
        }
    }

    // Actualiza todos los campos del buceador con el id indicado usando los datos del objeto recibido.
    // Devuelve true si se completó correctamente
    public static boolean modificar(int idBuceador, Buceador b) {

    String sql ="UPDATE buceadores SET dni = ?, nombre = ?, apellidos = ?, email = ?, telefono = ?, fecha_nacimiento = ?, fecha_alta = ?, titulacion = ?, organizacion = ?, num_inmersiones = ?, grupo_sanguineo = ?, alergias = ?, fecha_reconocimiento = ?, compania_seguro = ?, fecha_cad_seguro = ?, cont_emerg_nombre = ?, cont_emerg_telf = ? WHERE id_buceador = ?";

    try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            
            // Se mapean los atributos del buceador a cada '?' de la sentencia
            ps.setString(1, b.getDni());
            ps.setString(2, b.getNombre());
            ps.setString(3, b.getApellidos());
            ps.setString(4, b.getEmail());
            ps.setInt(5, b.getTelefono());
            ps.setDate(6, Date.valueOf(b.getFechaNacimiento()));
            ps.setDate(7, Date.valueOf(b.getFechaAlta()));
            ps.setString(8, b.getTitulacion().name());
            ps.setString(9, b.getOrganizacion().name());
            ps.setInt(10, b.getInmersiones());
            ps.setString(11, b.getGrupoSanguineo());
            ps.setString(12, b.getAlergias());
            ps.setDate(13, Date.valueOf(b.getFechaReconocimiento()));
            ps.setString(14, b.getCompaniaSeguro());
            ps.setDate(15, Date.valueOf(b.getFechaCaducidadSeguro()));
            ps.setString(16, b.getContEmergNombre());
            ps.setInt(17, b.getContEmergTelf());
            // El id va al final porque corresponde al WHERE de la sentencia
            ps.setInt(18, idBuceador);

            ps.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
            return false;
        }
    }
}
