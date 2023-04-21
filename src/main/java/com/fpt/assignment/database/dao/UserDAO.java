package com.fpt.assignment.database.dao;

public class UserDAO {
    public int add(T entity);
    public int remove(UUID id);
    public int update(T entity);
    public T searchById(UUID id);
    public List<T> fetchAll();
}
