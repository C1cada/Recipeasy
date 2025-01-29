package com.gabrielw.recipeasy.DAOs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gabrielw.recipeasy.Objects.Ingredients.IngredientComposite;

public class IngredientDAO implements DAO<IngredientComposite> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public IngredientComposite get(int id) {
        return null;
    }

    @Override
    public List<IngredientComposite> getAll(int id) {
        return null;
    }

    @Override
    public void add(IngredientComposite t) {
        upsert(t);
        for (IngredientComposite child : t.getValues()) {
            add(child);
        }
    }

    public void upsert(IngredientComposite t){
        boolean children = !t.getValues().isEmpty();
        String upsert = "INSERT INTO ingredients (key, name, quantity, children) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE name = ?, quantity = ?, children = ?;";
        jdbcTemplate.update(upsert,t.getKey(), t.getName(), t.getQuantity(), children, t.getName(), t.getQuantity(), children);
        String deleteRelations = "DELETE FROM ingredient_relations WHERE parent_id = ?;";
        jdbcTemplate.update(deleteRelations, t.getKey());
        String addRelations = "INSERT INTO ingredient_relations (parent, child) VALUES (?, ?);";
        for (IngredientComposite child : t.getValues()) {
            jdbcTemplate.update(addRelations, t.getKey(), child.getKey());
        }
    }


    @Override
    public void update(IngredientComposite t) {
    }

    @Override
    public void delete(IngredientComposite t) {
    }
}