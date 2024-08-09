package com.appjavajdbc.persistence.dao.core;

import java.sql.Connection;
import java.sql.DriverManager;

// Se cambio a una clase abstrata no se podra instanciar
// se debara usar extends
public abstract class ConnectionCore {

    /*    Esto es de forma didáctica para aprender   fundamentos, esto se haria directamente en la
     carpeta resource  en el archivo properties  con una sentencia de forma automatica       */

    // Con el throws indicamos que la clase que use este metodo necesita implementar un try catch

    public Connection getConnection() throws Exception {

        // Atributes

        String hostName = "localhost";
        String port = "5432";
        String dbName = "restaurant_management_db";
        String userName = "postgres";
        String password = "/S#hbG83/#??";

        //process


        // load driver

        Class.forName("org.postgresql.Driver");

        // url connection
        String url = "jdbc:postgresql://" + hostName + ":" + port + "/" + dbName;

        // result
        return DriverManager.getConnection(url, userName, password);


    }


}
