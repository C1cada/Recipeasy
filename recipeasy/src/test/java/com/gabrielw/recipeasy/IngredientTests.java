package com.gabrielw.recipeasy;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.gabrielw.recipeasy.Objects.Ingredients.Ingredient;
import com.gabrielw.recipeasy.Objects.Ingredients.IngredientContainer;

@SpringBootTest
class IngredientTests {

	@Test
	void contextLoads() {
	}

    @Test
    void testIngredient() {
        Ingredient ingredient = new Ingredient("Test Ingredient", "1");
        assertEquals("1 Test Ingredient", ingredient.getValue());
    }

    @Test
    void testIngredientContainter() {
        Ingredient ingredient = new Ingredient("Test Ingredient", "1");
        IngredientContainer container = new IngredientContainer("Sauce");
        container.add(ingredient);
        assertEquals("1 Test Ingredient", ingredient.getValue());
        assertEquals("Sauce", container.getValue());
        assertEquals(Arrays.asList(ingredient), container.getValues());
    }

}
