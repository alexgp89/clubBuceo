module com.mycompany.clubbuceo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    
    //INDICAMOS QUE SE DEBE USAR EL MODULO SQL. SI NO LO INDICAMOS AUNQUE TENGAMOS LAS DEPENDENCIAS EN EL PROYECTO NO SERAN LEIDAS
    requires java.sql;
    
    //DAMOS PERMISOS A JAVAFX PARA QUE PUEDA LEER LOS ARCHIVOS ALMACENADOS EN EL PAQUETE CLASES.
    opens com.mycompany.clubbuceo.clases to javafx.base;
    
    opens com.mycompany.clubbuceo to javafx.fxml;
    exports com.mycompany.clubbuceo;
}
