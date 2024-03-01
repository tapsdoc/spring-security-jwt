package com.example.Spring.SecurityAlibou;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SpringSecurityAlibouApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAlibouApplication.class, args);
	}

	//@Bean
	public CommandLineRunner runner() {
		return args -> {
			for (int i = 0; i < 1000; i++) {
				Faker faker = new Faker();
				String fname = faker.name().firstName();
				String lname = faker.name().lastName();
				int age = faker.number().numberBetween(16, 40);

				System.out.println(i+1 + ", " + fname + ", " + lname + ", " + age);
			}
		};
	}
}
