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

import com.gabrielw.recipeasy.Objects.Steps.InstructionComposite;
import com.gabrielw.recipeasy.Services.InstructionService;


@RestController
@RequestMapping("/api/v0/instructions")
public class InstructionController {

    @Autowired
    private InstructionService ingredientService;

    @GetMapping
    public List<InstructionComposite> getAllIngredients() {
        return ingredientService.getAll();
    }

    @GetMapping("/{key}")
    public ResponseEntity<InstructionComposite> getIngredientById(@PathVariable String key) {
        InstructionComposite ingredient = ingredientService.get(key);
        if (ingredient != null) {
            return ResponseEntity.ok(ingredient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public InstructionComposite addIngredient(@RequestBody InstructionComposite ingredient) {
        System.out.println(ingredient.toString());
        return ingredientService.add(ingredient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstructionComposite> updateIngredient(@RequestBody InstructionComposite ingredientDetails) {
        InstructionComposite updatedIngredient = ingredientService.update(ingredientDetails);
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