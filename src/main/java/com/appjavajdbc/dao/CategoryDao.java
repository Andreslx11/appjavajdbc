package com.appjavajdbc.dao;

import com.appjavajdbc.dao.core.CrudDao;
import com.appjavajdbc.entity.Category;



// Esta es muy especifica  para categoria lo ideal es que fuera
// generica para eso se creo en el package core una interface CrudDao generico

// ahora va extender CrudDao la interface generica

public interface CategoryDao  extends CrudDao<Category, Long> {


/*
// Era para borrar la antigua imeplementación especifica
     //  CRUD para la entida Category

     List<Category> findAll() throws Exception;
     Category findById(Long id) throws Exception;
     int create(Category category) throws Exception;
     int update(Long id, Category category) throws Exception;
     void deleteById(Long id ) throws Exception;
*/

// VENTAJA podriamos implementar otros metodos directmente de CategoryDao
}
