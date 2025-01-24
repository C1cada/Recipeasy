package com.gabrielw.recipeasy.DAOs;

import java.util.List;

public interface DAO<T> {
    public T get(int id);
    public List<T> getAll(int id);
    public void save(T t);
    public void update(T t);
    public void delete(T t);
}