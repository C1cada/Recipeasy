package com.gabrielw.recipeasy.Objects.Ingredients;

import java.util.List;
import java.util.UUID;

public class Ingredient implements IngredientComposite {
    private final String key;
    private final String name;
    private final String quantity;

    public Ingredient(String name, String quantity, String key) {
        this.name = name;
        this.quantity = quantity;
        this.key = key;
    }

    public Ingredient(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
        this.key = UUID.randomUUID().toString();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getQuantity() {
        return quantity;
    }

    @Override
    public String getKey() {
        return key;
    }
    
    @Override
    public List<IngredientComposite> getValues() {
        return null;
    }
}