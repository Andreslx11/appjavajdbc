package com.appjavajdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;



public class ConnectionCore {

    /*    Esto es de forma didáctica para aprender   fundamentos, esto se haria directamente en la
     carpeta resource  en el archivo properties  con una sentencia de forma automatica       */

    public Connection getConnection() {

        // Atributes

        String hostName = "localhost";
        String port ="3306";
        String dbName = "restaurant_management_db";
        String userName = "root";
        String password = "123456";

        //process



         try {
             // load driver
             Class.forName("com.mysql.cj.jdbc.Driver");

             // url connection
             String   url   =   "jdbc:mysql: //"  +   hostName    +  ":"  +  port  +  "/" + dbName;

             // result
             return DriverManager.getConnection(url, userName, password);

         } catch (Exception e){
             System.out.println("Connection::getConnection::CoreError: " +  e.getMessage());
         }

         return null;

    }
}
