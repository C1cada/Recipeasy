package com.gabrielw.recipeasy.DAOs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.gabrielw.recipeasy.Objects.Ingredients.Ingredient;
import com.gabrielw.recipeasy.Objects.Ingredients.IngredientComposite;
import com.gabrielw.recipeasy.Objects.Ingredients.IngredientContainer;

@Component
public class IngredientDAO implements DAO<IngredientComposite> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public IngredientComposite get(String key) {
        String query = "SELECT e.key, e.name, e.quantity, e.is_child, ARRAY_AGG(ingredient_children.child_id) " +
                    "FROM ingredients e " +
                    "LEFT JOIN ingredient_children ON e.key = ingredient_children.parent_id " +
                    "WHERE e.key = ? " +
                    "GROUP BY e.key ";
        List<IngredientComposite> ingredients = jdbcTemplate.query(query, new String[]{key}, (rs, rowNum) -> {
            if (rs.getString("array_agg") != null) {
                IngredientContainer c = new IngredientContainer(rs.getString("name"), rs.getString("key"));
                java.sql.Array sqlArray = rs.getArray("array_agg");
                String[] childArray = (String[]) sqlArray.getArray();
                for (String child : childArray) {
                    c.add(get(child));
                }
                return c;
            }
            return new Ingredient(rs.getString("key"), rs.getString("name"), rs.getString("quantity"));
        });
        if (ingredients.isEmpty()) {
            return null;
        }
        return ingredients.get(0);
    }

    @Override
    public List<IngredientComposite> getAll() {
        return null;
    }

    @Override
    public void add(IngredientComposite t) {
        if (t.getValues() != null) {
            for (IngredientComposite child : t.getValues()) {
                add(child);
            }
        }
        upsert(t);
    }

    private void upsert(IngredientComposite t){
        boolean children = (t.getValues() == null);
        String upsert = "INSERT INTO ingredients (key, name, quantity, is_child) VALUES (?, ?, ?, ?)" +
        "ON CONFLICT (key) DO UPDATE SET name = ?, quantity = ?, is_child = ?;";
        jdbcTemplate.update(upsert,t.getKey(), t.getName(), t.getQuantity(), children, t.getName(), t.getQuantity(), children);
        String deleteRelations = "DELETE FROM ingredient_children WHERE parent_id = ?;";
        jdbcTemplate.update(deleteRelations, t.getKey());
        String addRelations = "INSERT INTO ingredient_children (parent_id, child_id) VALUES (?, ?);";
        if (t.getValues() != null) {
            for (IngredientComposite child : t.getValues()) {
                jdbcTemplate.update(addRelations, t.getKey(), child.getKey());
            }
        }
    }


    @Override
    public void update(IngredientComposite t) {
        if (t.getValues() != null) {
            for (IngredientComposite child : t.getValues()) {
                update(child);
            }
        }
        upsert(t);
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

    private int del(String key, int i) {
        String delete = "DELETE FROM ingredients WHERE key = ?;";
        i += jdbcTemplate.update(delete, key);
        String deleteRelations = "DELETE FROM ingredient_children WHERE parent_id = ?;";
        i += jdbcTemplate.update(deleteRelations, key);
        return i;
    }
}