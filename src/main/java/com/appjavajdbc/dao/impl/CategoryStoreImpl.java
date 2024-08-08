package com.appjavajdbc.dao.impl;

import com.appjavajdbc.dao.CategoryDao;
import com.appjavajdbc.dao.ConnectionCore;
import com.appjavajdbc.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/*IMPORTANTE: es praticamente igual a class CategoryStoreImpl solo cambia
que aquí se usa LinkedList  y sqlQuery = "SELECT * FROM categories" , esto como ejemplo
que si por alguna necesdida  de hace mnuevas implementaciones es mejor hacerlas sobre otra
clase igual, e ir trabajando en la otra haciendo pruebas para no romper el programa*/
// Esta es muy especifica  para categoria lo ideal es que fuera
// generica para eso se creo en el package core una interface CrudDao generico

public class CategoryStoreImpl implements CategoryDao {
    @Override
    public List<Category> findAll() throws Exception {
        // Atributes

        List<Category> categories = new LinkedList<>();

        Category category;

        String sqlQuery;


        // sql query
        sqlQuery = "SELECT * FROM categories";

        try (         // ENTRE ()  van los recursos que se van cerrar de ultimo
                      /* De este modo con este try resource se van liberar recursos
                       automaticamente */

                      // Get connection
                      Connection connection = new ConnectionCore().getConnection();
                      // Prepare  Statement
                      PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                      //Execute  query
                      ResultSet resultSet = preparedStatement.executeQuery();

        ) {

            // Set data

            while (resultSet.next()) {

                category = new Category();

                category.setId(resultSet.getLong("id"));
                category.setName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
                category.setUrlKey(resultSet.getString("url_key"));
                category.setState(resultSet.getString("state"));

                // para manejar las fechas
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                if (createdAt != null) {
                    category.setCreatedAt(createdAt.toLocalDateTime());
                }

                Timestamp updatedAt = resultSet.getTimestamp("updated_at");
                if (updatedAt != null) {
                    category.setCreatedAt(updatedAt.toLocalDateTime());
                }


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
                Connection connection = new ConnectionCore().getConnection();
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

                    category = new Category();

                    category.setId(resultSet.getLong("id"));
                    category.setName(resultSet.getString("name"));
                    category.setDescription(resultSet.getString("description"));
                    category.setUrlKey(resultSet.getString("url_key"));
                    category.setState(resultSet.getString("state"));

                    // para manejar las fechas
                    Timestamp createdAt = resultSet.getTimestamp("created_at");
                    if (createdAt != null) {
                        category.setCreatedAt(createdAt.toLocalDateTime());
                    }

                    Timestamp updatedAt = resultSet.getTimestamp("updated_at");
                    if (updatedAt != null) {
                        category.setCreatedAt(updatedAt.toLocalDateTime());
                    }
                }
            }
        }
        return category;
    }

    @Override
    public int create(Category category) throws Exception {
        return 0;
    }

    @Override
    public int update(Long id, Category category) throws Exception {
        return 0;
    }

    @Override
    public void deleteById(Long id) throws Exception {

    }
}
