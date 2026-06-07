/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubbuceo.clases;
import java.sql.*;
/**
 *
 * @author Alejandro
 */

// Gestiona la conexión con la base de datos. Los parámetros de acceso son estáticos
// ya que son comunes a toda la aplicación.
public class Conexion {
    
    // Parámetros de conexión a la BD
    private static final String URL = "jdbc:mysql://localhost:3306/clubbuceo";
    private static final String USER = "root";
    private static final String PASS = "";

    // Devuelve una nueva conexión activa con la BD. Lanza SQLException si falla
    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
