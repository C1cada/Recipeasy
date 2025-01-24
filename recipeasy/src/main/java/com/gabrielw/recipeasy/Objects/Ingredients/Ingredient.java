package com.gabrielw.recipeasy.Objects.Ingredients;

import java.util.ArrayList;
import java.util.List;

public class Ingredient implements Composite {
    private final String name;
    private final String quantity;

    public Ingredient(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String getValue() {
        return quantity + " " + name;
    }

    @Override
    public List<Composite> getValues() {
        List<Composite> list = new ArrayList<>();
        list.add(this);
        return list;
    }
}