package com.appjavajdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionCore {

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
//             Class.forName("com.mysql.cj.jdbc.Driver");  es mysql
        Class.forName("org.postgresql.Driver");

        // url connection
        String url = "jdbc:postgresql://" + hostName + ":" + port + "/" + dbName;

        // result
        return DriverManager.getConnection(url, userName, password);


    }


}
