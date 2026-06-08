/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubbuceo.clases;
import com.mycompany.clubbuceo.enums.Organizacion;
import com.mycompany.clubbuceo.enums.Titulacion;
import java.io.Serializable;
import java.time.LocalDate;
/**
 *
 * @author Alejandro
 */
public class Buceador implements Serializable, Comparable<Buceador>{
    
    // Datos personales y de contacto
    private int id, telefono, contEmergTelf, inmersiones;
    private String dni, nombre, apellidos, email, companiaSeguro, contEmergNombre, grupoSanguineo, alergias;
    
    // Titulación de buceo y organización certificadora
    private Titulacion titulacion;
    private Organizacion organizacion;
    
    // Fechas relevantes del buceador
    private LocalDate fechaNacimiento, fechaAlta, fechaReconocimiento, fechaCaducidadSeguro;

    // Constructor completo — usado al recuperar un buceador existente (con id)
    public Buceador(int id, int telefono, int contEmergTelf, int inmersiones, String dni, String nombre, String apellidos, String email, String companiaSeguro, String contEmergNombre, String grupoSanguineo, String alergias, Titulacion titulacion, Organizacion organizacion, LocalDate fechaNacimiento, LocalDate fechaAlta, LocalDate fechaReconocimiento, LocalDate fechaCaducidadSeguro) {
        setId(id);
        setTelefono(telefono);
        setContEmergTelf(contEmergTelf);
        setInmersiones(inmersiones);
        setDni(dni);
        setNombre(nombre);
        setApellidos(apellidos);
        setEmail(email);
        setCompaniaSeguro(companiaSeguro);
        setContEmergNombre(contEmergNombre);
        setGrupoSanguineo(grupoSanguineo);
        setAlergias(alergias);
        setTitulacion(titulacion);
        setOrganizacion(organizacion);
        setFechaNacimiento(fechaNacimiento);
        setFechaAlta(fechaAlta);
        setFechaReconocimiento(fechaReconocimiento);
        setFechaCaducidadSeguro(fechaCaducidadSeguro);
    }

    // Constructor sin id — usado al crear un buceador nuevo; la BD asigna el id
    public Buceador(int telefono, int contEmergTelf, int inmersiones, String dni, String nombre, String apellidos, String email, String companiaSeguro, String contEmergNombre, String grupoSanguineo, String alergias, Titulacion titulacion, Organizacion organizacion, LocalDate fechaNacimiento, LocalDate fechaAlta, LocalDate fechaReconocimiento, LocalDate fechaCaducidadSeguro) {
        this(0, telefono, contEmergTelf, inmersiones, dni, nombre, apellidos, email, companiaSeguro, contEmergNombre, grupoSanguineo, alergias, titulacion, organizacion, fechaNacimiento, fechaAlta, fechaReconocimiento, fechaCaducidadSeguro);
    }
    
    // GETTERS
    public int getId() {
        return id;
    }

    public int getTelefono() {
        return telefono;
    }

    public int getContEmergTelf() {
        return contEmergTelf;
    }

    public int getInmersiones() {
        return inmersiones;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getCompaniaSeguro() {
        return companiaSeguro;
    }

    public String getContEmergNombre() {
        return contEmergNombre;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public String getAlergias() {
        return alergias;
    }

    public Titulacion getTitulacion() {
        return titulacion;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public LocalDate getFechaReconocimiento() {
        return fechaReconocimiento;
    }

    public LocalDate getFechaCaducidadSeguro() {
        return fechaCaducidadSeguro;
    }
     
    
    // SETTERS
    public void setId(int id) {
        this.id = id;
    }

    // Valida que el teléfono tenga exactamente 9 dígitos
    public void setTelefono(int telefono) {
        
        if(String.valueOf(telefono).length() != 9){
            throw new IllegalArgumentException("Telefono invalido. El campo se debe componer de 9 digitos");
        }else{
            this.telefono = telefono;
        }
    }
    
    // Valida que el teléfono de emergencias tenga exactamente 9 dígitos
    public void setContEmergTelf(int contEmergTelf) {
        if(String.valueOf(contEmergTelf).length() != 9){
            throw new IllegalArgumentException("Telefono de emergencias invalido. El campo se debe componer de 9 digitos");
        }else{
            this.contEmergTelf = contEmergTelf;
        }
        
    }

    public void setInmersiones(int inmersiones) {
        this.inmersiones = inmersiones;
    }
    
    // Valida el formato del DNI: 8 dígitos seguidos de una letra. Lo almacena en mayúsculas
    public void setDni(String dni) {
        if(dni == null || !dni.matches("\\d{8}[A-Za-z]")){
            throw new IllegalArgumentException("DNI invalido. Formato esperado: 8 digitos y una letra. Ejemplo: 12345678A");
        }else{
            this.dni = dni.toUpperCase();
        }
    }

    // Valida que el atributo no esté vacio
    public void setNombre(String nombre) {
        if(nombre == null || nombre.trim().isEmpty()){
            throw new IllegalArgumentException("No se ha indicado nombre");
        }else{
            this.nombre = nombre.trim().toUpperCase();
        }
    }

    // Valida que el atributo no esté vacio
    public void setApellidos(String apellidos) {
        if(apellidos == null || apellidos.trim().isEmpty()){
            throw new IllegalArgumentException("No se ha indicado apellidos");
        }else{
            this.apellidos = apellidos.trim().toUpperCase();
        }
    }

    // Valida que el email contenga '@' y '.'
    public void setEmail(String email) {
        if(!email.contains("@") && !email.contains(".")){
            throw new IllegalArgumentException("Email invalido. El email debe contener al menos '@' y '.'");
        }else{
            this.email = email.trim();
        }
    }

    // Valida que el atributo no esté vacio
    public void setCompaniaSeguro(String companiaSeguro) {
        if(companiaSeguro == null || companiaSeguro.trim().isEmpty()){
            throw new IllegalArgumentException("No se ha indicado compania de seguro");
        }else{
            this.companiaSeguro = companiaSeguro.trim().toUpperCase();
        }
    }

    // Valida que el atributo no esté vacio
    public void setContEmergNombre(String contEmergNombre) {
        if(contEmergNombre == null || contEmergNombre.trim().isEmpty()){
            throw new IllegalArgumentException("No se ha indicado nombre del contacto de emergencia");
        }
        this.contEmergNombre = contEmergNombre.trim().toUpperCase();
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    // Si no se indica ninguna alergia, se asigna "Ninguna" por defecto
    public void setAlergias(String alergias) {
        if(alergias == null || alergias.trim().isEmpty()){
            this.alergias = "Ninguna";
        }else{
            this.alergias = alergias.trim();
        }
    }

    public void setTitulacion(Titulacion titulacion) {
        this.titulacion = titulacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        if(fechaNacimiento == null){
            throw new IllegalArgumentException("No se ha indicado fecha de nacimiento");
        }else{
            this.fechaNacimiento = fechaNacimiento;
        }
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setFechaReconocimiento(LocalDate fechaReconocimiento) {
        if(fechaReconocimiento == null){
            throw new IllegalArgumentException("No se ha indicado fecha de reconocimiento medico");
        }else{
            this.fechaReconocimiento = fechaReconocimiento;
        }
    }

    public void setFechaCaducidadSeguro(LocalDate fechaCaducidadSeguro) {
        if(fechaCaducidadSeguro == null){
            throw new IllegalArgumentException("No se ha indicado fecha de caducidad del seguro");
        }else{
            this.fechaCaducidadSeguro = fechaCaducidadSeguro;
        }
    }
     
    // Dos buceadores son iguales si tienen el mismo DNI
    @Override
    public boolean equals(Object o){
        Buceador otro = (Buceador) o;
        return(getDni().equalsIgnoreCase(otro.getDni()));
    }
    
    // Orden natural por id
    @Override
    public int compareTo(Buceador o) {
        return getId() - o.getId();
    }
    
    // Muestra la ficha completa del buceador con todos sus datos
    @Override
    public String toString() {
    return  "─────────────────────────────────────────────────\n" +
            " #"+getId()+"  "+getNombre()+" "+getApellidos()+"\n"+
            "─────────────────────────────────────────────────\n" +
            "  DNI          : "+getDni()+"\n"+
            "  Nacimiento   : "+getFechaNacimiento()+"      Alta en el club: "+getFechaAlta()+"\n" +
            "  Email        : "+getEmail()+"\n"+
            "  Telefono     : "+getTelefono()+"\n\n" +
            "  Titulacion   : "+getTitulacion()+"   Org.: "+getOrganizacion()+"\n" +
            "  Inmersiones  : "+getInmersiones()+"\n\n" +
            "  Grupo sang.  : "+getGrupoSanguineo()+"   Alergias: "+getAlergias()+"\n\n"+
            "  Rec. medico  : "+getFechaReconocimiento() + "\n" +
            "  Seguro       : "+getCompaniaSeguro()+"  (cad. "+getFechaCaducidadSeguro()+")\n\n" +
            "  Cont. emergencia: "+getContEmergNombre()+"  —  "+getContEmergTelf()+"\n";
    }
    
}
