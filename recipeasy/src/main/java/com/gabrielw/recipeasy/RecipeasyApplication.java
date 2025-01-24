package com.gabrielw.recipeasy;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RecipeasyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeasyApplication.class, args);
	}
}
