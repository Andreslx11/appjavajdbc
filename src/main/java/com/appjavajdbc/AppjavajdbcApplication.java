package com.appjavajdbc;

import com.appjavajdbc.dao.CategoryDao;


import com.appjavajdbc.dao.impl.CategoryDaoImpl;
import com.appjavajdbc.entity.Category;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class AppjavajdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppjavajdbcApplication.class, args);


/* Era solo para ensayar la conexión a la base de datos
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


            CategoryDao categoryDao = new CategoryDaoImpl();


/*
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
  */

            Category category =  categoryDao.findById(1L);
            System.out.println("Id: " + category.getId());
            System.out.println("Name: " + category.getName());
            System.out.println("Description: " + category.getDescription());
            System.out.println("Url key: " + category.getUrlKey());
            System.out.println("State: " + category.getState());
            System.out.println("Created at: " + category.getCreatedAt());
            System.out.println();

        }  catch (Exception e){
            System.out.println("Error: " + e.getMessage());

        }
	}

}
