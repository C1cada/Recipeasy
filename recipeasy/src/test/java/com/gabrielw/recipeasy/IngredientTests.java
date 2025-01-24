package com.gabrielw.recipeasy;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.gabrielw.recipeasy.Objects.Ingredients.Container;
import com.gabrielw.recipeasy.Objects.Ingredients.Ingredient;

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
        Container container = new Container();
        container.add(ingredient);
        assertEquals("1 Test Ingredient", ingredient.getValue());
        assertEquals("1 Test Ingredient", container.getValue());
        assertEquals(Arrays.asList(ingredient), container.getValues());
    }

}
