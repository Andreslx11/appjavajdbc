package com.appjavajdbc;

import com.appjavajdbc.persistence.dao.CategoryDao;
import com.appjavajdbc.persistence.dao.impl.CategoryDaoImpl;
import com.appjavajdbc.persistence.entity.Category;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.List;


@SpringBootApplication
public class AppjavajdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppjavajdbcApplication.class, args);


  /*
         // Era solo para ensayar la conexión a la base de datos
		try {
			ConnectionCore connection = new ConnectionCore();
			connection.getConnection();
			System.out.println("Conexión establecida correctamente.");

		}catch (Exception e) {
			System.out.println("Error al conectar con la base de datos: " + e.getMessage());
			e.printStackTrace();
		}
*/

        try {

            // implmentacion  CategoryDaoImpl
            CategoryDao categoryDao = new CategoryDaoImpl();

            // implmentacion  CategoryStoreImpl
            // CategoryDao categoryDao = new CategoryStoreImpl();


            // Listar todo

            // Listar todo
            List<Category> categories = categoryDao.findAll();

            System.out.println("Categories  start: ");
            for (Category category : categories) {
                System.out.println("Id: " + category.getId());
                System.out.println("Name: " + category.getName());
                System.out.println("Description: " + category.getDescription());
                System.out.println("Url key: " + category.getUrlKey());
                System.out.println("State: " + category.getState());
                System.out.println("Created at: " + category.getCreatedAt());
                System.out.println();
            }

            System.out.println("Categories  end.");


            // Obtener
/*           // Obtener
            Category category =  categoryDao.findById(1L);
            System.out.println("Id: " + category.getId());
            System.out.println("Name: " + category.getName());
            System.out.println("Description: " + category.getDescription());
            System.out.println("Url key: " + category.getUrlKey());
            System.out.println("State: " + category.getState());
            System.out.println("Created at: " + category.getCreatedAt());
            System.out.println();
*/


            // Crear
/*
            // Crear
            Category category = new Category();
            category.setName("Categoria A2");
            category.setDescription("Sin detalles");
            category.setUrlKey("categoria-A2");
            category.setState("A");
            category.setCreatedAt(LocalDateTime.now());

            int result = categoryDao.create(category);

            System.out.println("Create: " + result);
*/

            // Actualizar
/*
            /// Actualizar

            Category category = new Category();
            category.setName("Categoria A");
            category.setDescription("Sin detalles");
            category.setUrlKey("categoria-a");
            category.setUpdatedAt(LocalDateTime.now());

            int result = categoryDao.update(96L, category);

            System.out.println("Update: " + result);

            */


            // Eliminación

/*
            categoryDao.deleteById(99L);
            System.out.println("deleteById: ");

*/

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        }
    }






}
