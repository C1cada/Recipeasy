package com.gabrielw.recipeasy.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielw.recipeasy.Objects.Ingredients.IngredientComposite;
import com.gabrielw.recipeasy.Services.IngredientService;


@RestController
@RequestMapping("/api/v0/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public List<IngredientComposite> getAllIngredients() {
        return ingredientService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientComposite> getIngredientById(@PathVariable String key) {
        IngredientComposite ingredient = ingredientService.get(key);
        if (ingredient != null) {
            return ResponseEntity.ok(ingredient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public IngredientComposite addIngredient(@RequestBody IngredientComposite ingredient) {
        return ingredientService.add(ingredient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientComposite> updateIngredient(@RequestBody IngredientComposite ingredientDetails) {
        IngredientComposite updatedIngredient = ingredientService.update(ingredientDetails);
        if (updatedIngredient != null) {
            return ResponseEntity.ok(updatedIngredient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable String key) {
        int isDeleted = ingredientService.delete(key);
        if (isDeleted > 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}