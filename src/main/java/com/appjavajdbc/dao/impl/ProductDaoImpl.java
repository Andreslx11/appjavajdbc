package com.appjavajdbc.dao.impl;

import com.appjavajdbc.dao.ProductDao;
import com.appjavajdbc.entity.Product;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> findAll() throws Exception {
        return List.of();
    }

    @Override
    public Product findById(Long aLong) throws Exception {
        return null;
    }

    @Override
    public int create(Product entity) throws Exception {
        return 0;
    }

    @Override
    public int update(Long aLong, Product entity) throws Exception {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) throws Exception {

    }
}
