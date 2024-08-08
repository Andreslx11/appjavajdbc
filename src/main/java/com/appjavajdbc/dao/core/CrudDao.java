package com.appjavajdbc.dao.core;



import java.util.List;

public interface CrudDao<T, ID> {

    // Interface   generica para CRUD

    List<T> findAll() throws Exception;

    T findById(ID id) throws Exception;

    int create(T entity) throws Exception;

    int update(ID id, T entity) throws Exception;

    void deleteById(ID id ) throws Exception;
}