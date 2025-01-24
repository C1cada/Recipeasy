package com.gabrielw.recipeasy.Objects.Ingredients;

import java.util.ArrayList;
import java.util.List;

public class IngredientContainer implements Composite {
    private final List<Composite> children;
    private final String name;

    public IngredientContainer(String name) {
        this.name = name;
        children = new ArrayList<>();
    }

    public void add(Composite child) {
        children.add(child);
    }

    public void remove(Composite child) {
        children.remove(child);
    }

    @Override
    public List<Composite> getValues() {
        List<Composite> values = new ArrayList<>();
        for (Composite child : children) {
            values.addAll(child.getValues());
        }
        return values;
    }

    @Override
    public String getValue() {
        return name;
    }
}