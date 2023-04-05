package revision.pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import revision.pizza.util.Tester;

@SpringBootApplication
public class PizzaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzaApplication.class, args);
		// Tester.jsonTester();
	}

}
