package com.fpt.assignment.database.dao;

import java.util.List;
import java.util.UUID;

public interface InterfaceDAO<T> {
    public int add(T entity);
    public int remove(UUID id);
    public int update(T entity);
    public T searchById(UUID id);
    public List<T> fetchAll();
}
