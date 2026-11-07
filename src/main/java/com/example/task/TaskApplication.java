package com.example.task;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(TaskRepository repository) {
        return args -> {
            Task task = new Task("Complete Assignment", "Spring Boot JPA Testing");
            repository.save(task);
            System.out.println("=================================================");
            System.out.println("Application started with sample data in H2 database!");
            System.out.println("Open H2 Console at: http://localhost:8080/h2-console");
            System.out.println("JDBC URL: jdbc:h2:mem:taskdb");
            System.out.println("=================================================");
        };
    }
}
