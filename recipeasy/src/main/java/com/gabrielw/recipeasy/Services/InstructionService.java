/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.gabrielw.recipeasy.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gabrielw.recipeasy.DAOs.InstructionDAO;
import com.gabrielw.recipeasy.Objects.Steps.InstructionComposite;

@Component
public class InstructionService {
    @Autowired
    public InstructionDAO ingredientDAO; 

    public List<InstructionComposite> getAll() {
        return ingredientDAO.getAll();
    }

    public InstructionComposite get(String key) {
        return ingredientDAO.get(key);
    }

    public InstructionComposite add(InstructionComposite ingredient) {
        ingredientDAO.add(ingredient);
        return ingredient;
    }

    public InstructionComposite update(InstructionComposite updatedIngredient) {
        ingredientDAO.update(updatedIngredient);
        return updatedIngredient;
    }

    public int delete(String key) {
        return ingredientDAO.delete(key);
    }
}
