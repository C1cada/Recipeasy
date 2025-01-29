package com.gabrielw.recipeasy.Objects.Ingredients;

import java.util.List;

public interface IngredientComposite {
    public abstract String getKey();
    public abstract String getName();
    public abstract String getQuantity();
    public abstract List<IngredientComposite> getValues();
}