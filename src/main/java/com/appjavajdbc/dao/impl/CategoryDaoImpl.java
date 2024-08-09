package com.appjavajdbc.dao.impl;


import com.appjavajdbc.dao.CategoryDao;
import com.appjavajdbc.dao.core.ConnectionCore;
import com.appjavajdbc.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


// Esta es muy especifica  para categoria lo ideal es que fuera
// generica para eso se creo en el package core una interface CrudDao generico

// Como la clase ConnectionCore se cambio a abstrata se debe extends

public class CategoryDaoImpl  extends ConnectionCore implements CategoryDao {

   // Logica para el CRUD


    @Override
    public List<Category> findAll() throws Exception {
        // Atributes

        List<Category> categories = new ArrayList<>();

        Category category;

        String sqlQuery;


        // sql query
        sqlQuery = "SELECT id, name, description, url_key, state, created_at, updated_at from categories";

        try (         // ENTRE ()  van los recursos que se van cerrar de ultimo
                      /* De este modo con este try resource se van liberar recursos
                       automaticamente */

                      // Get connection
                      /* como ya ConnectionCore es un class abstract no se puede instanciar,
                        en este caso ya la clase CategoryDaoImpl  extiende ConnectionCore
                        por lo cual puede usar sus metodos

                       // Connection connection = new ConnectionCore().getConnection();

                        */

                      Connection connection = getConnection();

                      // Prepare  Statement
                      PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                      //Execute  query
                      ResultSet resultSet = preparedStatement.executeQuery();

        ) {

            // Set data

            while (resultSet.next()) {

                category = mapResultSetToCategory(resultSet);



                // vamos añadir cada objeto creado a la lista
                categories.add(category);
            }

        }

        //result
        return categories;
    }

    @Override
    public Category findById(Long id) throws Exception {
        // Attributes
        Category category = null;

        String sqlQuery;


        // process
        // No lo concatenamos con "+" por que no es una buena pratica, mejor se parametriza con "?"
        sqlQuery = "select  id, name,  description, url_key, state,  created_At, updated_at from categories  where id= ?";

        try (
                // Abro la conexion y prepararo la sentencia

                // Get connection
                 Connection connection =getConnection();

                // Prepare  Statement
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        ) {

            // Set parameter
            preparedStatement.setLong(1, id);

            try (
                    //Execute  query
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                // Set data

                if (resultSet.next()) {

                    category = mapResultSetToCategory(resultSet);

                }
            }
        }
        return category;
    }


    @Override
    public int create(Category category) throws Exception {

        // Attributes

        int result = 0;
        String sqlQuery;


        // process

        sqlQuery = "insert into categories (name, description, url_key, state, created_at) values(?, ?, ?, ?, ?)";


        try (
                // Connection
                Connection connection =getConnection();

                // Prepared Stetament
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        ) {

          /* como la consulta sqlQuery tiene parametros no se le puden asignar dentro
             de los parentesis del try resources  */

            // Set parameter
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.setString(3, category.getUrlKey());
            preparedStatement.setString(4, category.getState());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(category.getCreatedAt()));


            // execute query
            // executeUpdate() retorna 1 un valor positivo si realizo la operación y un
            // valor negativo si no pudo realizar la operación
            result = preparedStatement.executeUpdate();


        }

        // Result
        return result;
    }


    @Override
    public int update(Long id, Category category) throws Exception {

        // Attributes

        int result = 0;
        String sqlQuery;


        // process

        sqlQuery = "update categories set name = ?, description = ?, url_key = ?, updated_at =? where id = ?";


        try (
                // Connection
                Connection connection =getConnection();

                // Prepared Stetament
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        ) {

          /* como la consulta sqlQuery tiene parametros no se le puden asignar dentro
             de los parentesis del try resources  */

            // Set parameter
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.setString(3, category.getUrlKey());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(category.getUpdatedAt()));
            preparedStatement.setLong(5, id);


            // execute query
            // executeUpdate() retorna 1 un valor positivo si realizo la operación y un
            // valor negativo si no pudo realizar la operación
            result = preparedStatement.executeUpdate();


        }

        // Result
        return result;
    }

    @Override
    public void deleteById(Long id) throws Exception {
      // Attributes

        String sqlQuery;


        // process

        sqlQuery = "delete from categories where id =?";


        try (
                // Connection
                Connection connection = getConnection();

                // Prepared Stetament
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        ) {


            // Set parameter
            preparedStatement.setLong(1, id);


            // Execute query
            preparedStatement.executeUpdate();


        }


    }

    // Vamos a mappear lo que nos devuelve resultset a la category
    //
    private Category mapResultSetToCategory(ResultSet resultSet) throws Exception {
        // Attributes
        Category category = new Category();



        // Process

        category.setId(resultSet.getLong("id"));
        category.setName(resultSet.getString("name"));
        category.setDescription(resultSet.getString("description"));
        category.setUrlKey(resultSet.getString("url_key"));
        category.setState(resultSet.getString("state"));

        Timestamp createdAt = resultSet.getTimestamp("created_at");
        if (createdAt != null) {
            category.setCreatedAt(createdAt.toLocalDateTime());
        }

        Timestamp updatedAt = resultSet.getTimestamp("updated_at");
        if (updatedAt != null) {
            category.setUpdatedAt(updatedAt.toLocalDateTime());
        }

        // Result

        return category;
    }



}

