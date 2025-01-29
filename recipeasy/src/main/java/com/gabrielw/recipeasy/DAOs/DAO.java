package com.gabrielw.recipeasy.DAOs;

import java.util.List;

public interface DAO<T> {
    public T get(int id);
    public List<T> getAll(int id);
    public void add(T t);
    public void update(T t);
    public void delete(T t);
}