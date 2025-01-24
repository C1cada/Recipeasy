package com.gabrielw.recipeasy;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Autowired
    private JdbcTemplate jdbcTemplate;

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		String sql = "INSERT INTO users (username) VALUES ("
                + "'Lahiru Ariyasinghe')";
         
        int rows = jdbcTemplate.update(sql);
        if (rows > 0) {
            System.out.println("A new row has been inserted.");
        }

		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
