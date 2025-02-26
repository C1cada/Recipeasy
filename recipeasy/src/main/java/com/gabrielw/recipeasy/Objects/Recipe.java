package com.gabrielw.recipeasy.Objects;

import java.util.ArrayList;

import com.gabrielw.recipeasy.Objects.Ingredients.IngredientComposite;
import com.gabrielw.recipeasy.Objects.Steps.InstructionComposite;

public class Recipe {
    private String key;
    private String name;
    private String description;
    private int servings;
    private int prepTime;
    private int cookTime;
    private int totalTime;
    private IngredientComposite ingredient;
    private InstructionComposite instruction;
    private ArrayList<Tag> tags;

    public Recipe(
        String key,
        String name, 
        String description,
        int servings,
        int prepTime,
        int cookTime,
        int totalTime, 
        IngredientComposite ingredient, 
        InstructionComposite instruction, 
        ArrayList<Tag> tags ) {
        this.key = key;
        this.name = name;
        this.description = description;
        this.servings = servings;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.totalTime = totalTime;
        this.ingredient = ingredient;
        this.instruction = instruction;
        this.tags = tags;
    }

    public Recipe(
        String key, 
        String name, 
        IngredientComposite ingredient, 
        InstructionComposite instruction, 
        String description, 
        int servings, 
        int prepTime, 
        int cookTime, 
        int totalTime) {
        this.key = key;
        this.name = name;
        this.description = description;
        this.servings = servings;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.totalTime = totalTime;
        this.ingredient = ingredient;
        this.instruction = instruction;
        this.tags = new ArrayList<>();
    }

    public Recipe(String key, String name) {
        this.key = key;
        this.name = name;
        this.tags = new ArrayList<>();
    }

    public IngredientComposite getIngredients() {
        return ingredient;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setIngredients(IngredientComposite ingredient) {
        this.ingredient = ingredient;
    }

    public InstructionComposite getInstructions() {
        return instruction;
    }

    public void setInstructions(InstructionComposite instruction) {
        this.instruction = instruction;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}