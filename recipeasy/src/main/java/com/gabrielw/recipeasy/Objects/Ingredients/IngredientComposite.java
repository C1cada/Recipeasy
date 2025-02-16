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
                return new Ingredient(key, name, quantity);
            }
        } else {
            IngredientContainer con;     
            if (key == null){
                con = new IngredientContainer(name);
            } else {
                con = new IngredientContainer(name, key);
            }
            for (IngredientComposite value : values){
                con.add(create(value.getKey(), value.getName(), value.getQuantity(), value.getValues()));
            }
            return con;
        }
    }
}