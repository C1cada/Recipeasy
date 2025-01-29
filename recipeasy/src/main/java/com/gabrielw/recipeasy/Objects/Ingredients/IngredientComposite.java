package com.gabrielw.recipeasy.Objects.Ingredients;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public interface IngredientComposite {
    public abstract String getKey();
    public abstract String getName();
    public abstract String getQuantity();
    public abstract List<IngredientComposite> getValues();

    @JsonCreator
    public static IngredientComposite create(@JsonProperty("key") String key,
                                       @JsonProperty("name") String name,
                                       @JsonProperty("quantity") String quantity,
                                       @JsonProperty("values") List<IngredientComposite> values) {
        if (values == null){
            if (key == null){
                return new Ingredient(name, quantity);
            } else {
                return new Ingredient(name, quantity, key);
            }
        } else {
            if (key == null){
                return new IngredientContainer(name);
            } else {
                return new IngredientContainer(name, key);
            }
        }
    }
}