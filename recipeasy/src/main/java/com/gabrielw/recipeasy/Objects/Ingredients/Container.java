package com.gabrielw.recipeasy.Objects.Ingredients;

import java.util.ArrayList;
import java.util.List;

public class Container implements Composite {
    private final List<Composite> children;

    public Container() {
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
        StringBuilder value = new StringBuilder();
        for (Composite child : children) {
            value.append(child.getValue());
            value.append("\n");
        }
        if (value.length() > 0) {
            value.setLength(value.length() - 1);
        }
        return value.toString();
    }
}