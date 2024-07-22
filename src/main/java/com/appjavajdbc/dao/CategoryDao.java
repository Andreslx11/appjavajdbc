package com.appjavajdbc.dao;

import com.appjavajdbc.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    public List<Category> findAll() {

        // Atributes

        List<Category> categories = new ArrayList<>();

        Category category;

        String sqlQuery;


        /* IMPORANTE estos objetos cuando  apertura una conexión a la base datos, van
         que quedar ocupando espacio en memoria  por lo cual es importante cerrarlos al
         terminar la operación para que no gasten recursos memoria, para eso se debe
         hacer a la  inversa primero se cierra ResultSet -> PrepereStatement -> Connection
         al final de todo asi sea que no se pudo hacer la conexión */

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;  // para leer la infromación recuperada de la base datos


        // process
        // try por si la base datos no responde

        try {


            // sql query
            sqlQuery = "SELECT id, name, description, url_key, state, created_at, updated_at from categories";

            // Get connection
            connection = new ConnectionCore().getConnection();

            // Prepare  Statement
            preparedStatement = connection.prepareStatement(sqlQuery);

            //Execute  query
             resultSet =  preparedStatement.executeQuery();

             // Set data

            while (resultSet.next()){

                category = new Category();

                category.setId(resultSet.getLong("id"));
                category.setName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
                category.setUrlKey(resultSet.getString("url_key"));
                category.setState(resultSet.getString("state"));

                // para manejar las fechas
                Timestamp  createdAt = resultSet.getTimestamp("created_at");
                if(createdAt != null){
                    category.setCreatedAt(createdAt.toLocalDateTime());
                }

                Timestamp updatedAt = resultSet.getTimestamp("updated_at");
                if(updatedAt != null){
                    category.setCreatedAt(updatedAt.toLocalDateTime());
                }


                // vamos añadir cada objeto creado a la lista
                categories.add(category);
            }

        } catch (Exception e) {
            System.out.println("CategoryDao::findAll::Error" + e.getMessage());
        }
        finally {
            // Cerrar o liberar espacio

            try {

                if (resultSet != null){
                    resultSet.close();
                }

            } catch (Exception e){
                System.out.println("CategoryDao::findAll::Finally:  " +  e.getMessage());
            }

        }


        //result

        return  categories;

    }
}
