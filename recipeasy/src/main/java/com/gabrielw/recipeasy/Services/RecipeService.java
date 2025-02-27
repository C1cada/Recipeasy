/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.gabrielw.recipeasy.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gabrielw.recipeasy.DAOs.RecipeDAO;
import com.gabrielw.recipeasy.Objects.Recipe;

@Component
public class RecipeService {
    @Autowired
    public RecipeDAO ingredientDAO; 

    public List<Recipe> getAll() {
        return ingredientDAO.getAll();
    }

    public Recipe get(String key) {
        return ingredientDAO.get(key);
    }

    public Recipe add(Recipe ingredient) {
        ingredientDAO.add(ingredient);
        return ingredient;
    }

    public Recipe update(Recipe updatedRecipe) {
        ingredientDAO.update(updatedRecipe);
        return updatedRecipe;
    }

    public int delete(String key) {
        return ingredientDAO.delete(key);
    }
}
