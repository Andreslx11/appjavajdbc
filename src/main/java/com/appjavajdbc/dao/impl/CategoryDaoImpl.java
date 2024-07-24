package com.appjavajdbc.dao.impl;


import com.appjavajdbc.dao.CategoryDao;
import com.appjavajdbc.dao.ConnectionCore;
import com.appjavajdbc.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class CategoryDaoImpl implements CategoryDao {
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

}

