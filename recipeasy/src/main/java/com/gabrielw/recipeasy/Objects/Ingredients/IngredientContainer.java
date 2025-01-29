package com.gabrielw.recipeasy.Objects.Ingredients;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IngredientContainer implements IngredientComposite {
    private final String key;
    private final List<IngredientComposite> children;
    private final String name;
    private final String quantity;

    public IngredientContainer(String name, String key) {
        this.name = name;
        this.key = key;
        this.quantity = null;
        children = new ArrayList<>();
    }

    public IngredientContainer(String name) {
        this.name = name;
        this.key = UUID.randomUUID().toString();
        this.quantity = null;
        children = new ArrayList<>();
    }

    public void add(IngredientComposite child) {
        children.add(child);
    }

    public void remove(IngredientComposite child) {
        children.remove(child);
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
        return children;
    }
}