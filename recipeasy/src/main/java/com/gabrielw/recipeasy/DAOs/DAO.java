package com.gabrielw.recipeasy.DAOs;

import java.util.List;

public interface DAO<T> {
    public T get(String key);
    public List<T> getAll();
    public void add(T t);
    public void update(T t);
    public int delete(String key);
}