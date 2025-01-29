package com.gabrielw.recipeasy.DAOs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.gabrielw.recipeasy.Objects.Ingredients.IngredientComposite;

@Component
public class IngredientDAO implements DAO<IngredientComposite> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public IngredientComposite get(String key) {
        // String recursiveQuery = 
        // "WITH RECURSIVE ingredient_tree AS " +
        // "(SELECT * FROM ingredients WHERE key = ?" +
        // "UNION SELECT i.* FROM ingredients i JOIN ingredient_relations" +
        // "ir ON i.key = ir.child WHERE ir.parent = ?)" +
        // " SELECT * FROM ingredient_tree;";
        return null;
    }

    @Override
    public List<IngredientComposite> getAll() {
        return null;
    }

    @Override
    public void add(IngredientComposite t) {
        upsert(t);
        for (IngredientComposite child : t.getValues()) {
            add(child);
        }
    }

    private void upsert(IngredientComposite t){
        boolean children = (t.getValues() == null);
        String upsert = "INSERT INTO ingredients (key, name, quantity, children) VALUES (?, ?, ?, ?)" +
        "ON CONFLICT (id) DO UPDATE SET name = ?, quantity = ?, children = ?;";
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
        upsert(t);
        for (IngredientComposite child : t.getValues()) {
            update(child);
        }
    }

    @Override
    public int delete(String key) {
        IngredientComposite t = get(key);
        int i = 0;
        i += del(t.getKey(), i);
        for (IngredientComposite child : t.getValues()) {
            i += delete(child, i);
        }
        return i;
    }

    public int delete(IngredientComposite t, int i) {
        i += del(t.getKey(), i);
        for (IngredientComposite child : t.getValues()) {
            i += delete(child, i);
        }
        return i;
    }

    private int del(String id, int i) {
        String delete = "DELETE FROM ingredients WHERE key = ?;";
        i += jdbcTemplate.update(delete, id);
        String deleteRelations = "DELETE FROM ingredient_relations WHERE parent_id = ?;";
        i += jdbcTemplate.update(deleteRelations, id);
        return i;
    }
}