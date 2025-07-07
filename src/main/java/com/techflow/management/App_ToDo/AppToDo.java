package com.techflow.management.App_ToDo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppToDo {

	public static void main(String[] args) {
		SpringApplication.run(AppToDo.class, args);
	}

	@Bean
	CommandLineRunner initialization () {
		return args -> {

			System.out.println();
			System.out.println("Access the app here: http://localhost:8080/get_tasks");
		};
	}

} 