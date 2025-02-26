package com.gabrielw.recipeasy.DAOs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.gabrielw.recipeasy.Objects.Ingredients.IngredientComposite;
import com.gabrielw.recipeasy.Objects.Recipe;
import com.gabrielw.recipeasy.Objects.Steps.InstructionComposite;
import com.gabrielw.recipeasy.Objects.Tag;

@Component
public class RecipeDAO implements DAO<Recipe>{
    @Autowired
    public IngredientDAO ingredientDAO;

    @Autowired
    public InstructionDAO instructionDAO;

    @Autowired
    public TagDAO tagDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Recipe get(String key) {
        String query = "SELECT r.key, r.name, r.description, r.ingredient_id, r.instruction_id, r.servings, r.prep_time, r.cook_time, r.total_time, ARRAY_AGG(recipe_tags.tag_id)" +
                    "FROM recipes r " +
                    "LEFT JOIN recipe_tags ON r.key = recipe_tags.recipe_id " +
                    "WHERE r.key = ? " +
                    "GROUP BY r.key ";  

        List<Recipe> recipes = jdbcTemplate.query(query, (rs, rowNum) -> {
            InstructionComposite instruction = instructionDAO.get(rs.getString("instruction_id"));
            IngredientComposite ingredient = ingredientDAO.get(rs.getString("ingredient_id"));
            Recipe c = new Recipe(
                rs.getString("key"),
                rs.getString("name"),
                ingredient,
                instruction,
                rs.getString("description"),
                rs.getInt("servings"),
                rs.getInt("prep_time"),
                rs.getInt("cook_time"),
                rs.getInt("total_time")
                );
            if (rs.getString("array_agg") != null) {
                java.sql.Array sqlArray = rs.getArray("array_agg");
                String[] childArray = (String[]) sqlArray.getArray();
                for (String child : childArray) {
                    c.addTag(tagDAO.get(child));
                }
            }
            return c;
        }, key);
        if (recipes.isEmpty()) {
            return null;
        }
        return recipes.get(0);
    }

    @Override
    public List<Recipe> getAll() {
        String query = "SELECT r.key, r.name, r.description, r.ingredient_id, r.instruction_id, r.servings, r.prep_time, r.cook_time, r.total_time, ARRAY_AGG(recipe_tags.tag_id)" +
                    "FROM recipes r " +
                    "LEFT JOIN recipe_tags ON r.key = recipe_tags.recipe_id " +
                    "GROUP BY r.key ";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            InstructionComposite instruction = instructionDAO.get(rs.getString("instruction_id"));
            IngredientComposite ingredient = ingredientDAO.get(rs.getString("ingredient_id"));
            Recipe c = new Recipe(
                rs.getString("key"),
                rs.getString("name"),
                ingredient,
                instruction,
                rs.getString("description"),
                rs.getInt("servings"),
                rs.getInt("prep_time"),
                rs.getInt("cook_time"),
                rs.getInt("total_time")
                );
            if (rs.getString("array_agg") != null) {
                java.sql.Array sqlArray = rs.getArray("array_agg");
                String[] childArray = (String[]) sqlArray.getArray();
                for (String child : childArray) {
                    c.addTag(tagDAO.get(child));
                }
            }
            return c;
        });
    }

    private void upsert(Recipe r) {
        String upsert = "INSERT INTO recipes (key, name, description, ingredient_id, instruction_id, servings, prep_time, cook_time, total_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)" +
        "ON CONFLICT (key) DO UPDATE SET name = ?, description = ?, ingredient_id = ?, instruction_id = ?, servings = ?, prep_time = ?, cook_time = ?, total_time = ?;";
        jdbcTemplate.update(upsert, r.getKey(), r.getName(), r.getDescription(), r.getIngredients().getKey(), r.getInstructions().getKey(), r.getServings(), r.getPrepTime(), r.getCookTime(), r.getTotalTime(), r.getName(), r.getDescription(), r.getIngredients().getKey(), r.getInstructions().getKey(), r.getServings(), r.getPrepTime(), r.getCookTime(), r.getTotalTime());
        String deleteRelations = "DELETE FROM recipe_tags WHERE recipe_id = ?;";
        jdbcTemplate.update(deleteRelations, r.getKey());
        String addRelations = "INSERT INTO recipe_tags (recipe_id, tag_id) VALUES (?, ?);";
        for (Tag tag : r.getTags()) {
            tagDAO.add(tag);
            jdbcTemplate.update(addRelations, r.getKey(), tag.getKey());
        }
    }

    @Override
    public void add(Recipe r) {
        upsert(r);
    }

    @Override
    public void update(Recipe r) {
        upsert(r);
    }

    @Override
    public int delete(String key) {
        String deleteRelations = "DELETE FROM recipe_tags WHERE recipe_id = ?;";
        jdbcTemplate.update(deleteRelations, key);
        String query = "DELETE FROM recipes WHERE key = ?";
        return jdbcTemplate.update(query, key);
    }

}
