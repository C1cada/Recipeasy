package com.gabrielw.recipeasy.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gabrielw.recipeasy.DAOs.IngredientDAO;
import com.gabrielw.recipeasy.Objects.Ingredients.IngredientComposite;


public class IngredientService {

    @Autowired
    public IngredientDAO ingredientDAO; 

    public List<IngredientComposite> getAll() {
        return ingredientDAO.getAll();
    }

    public IngredientComposite get(String key) {
        return ingredientDAO.get(key);
    }

    public IngredientComposite add(IngredientComposite ingredient) {
        ingredientDAO.add(ingredient);
        return ingredient;
    }

    public IngredientComposite update(IngredientComposite updatedIngredient) {
        ingredientDAO.update(updatedIngredient);
        return updatedIngredient;
    }

    public int delete(String key) {
        return ingredientDAO.delete(key);
    }
}