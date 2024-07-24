package com.appjavajdbc.dao;

import com.appjavajdbc.entity.Category;

import java.util.List;

public interface CategoryDao {

     List<Category> findAll() throws Exception;
     Category findById(Long id) throws Exception;





}
