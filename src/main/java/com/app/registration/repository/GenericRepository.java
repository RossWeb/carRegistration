package com.app.registration.repository;

/**
 * Created by User on 2016-11-05.
 */
public interface GenericRepository<T extends Object> {

    T create(T entity);

    T update(T entity);

    void remove(T entity);
}
