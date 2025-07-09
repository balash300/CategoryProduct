package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.categoryProduct")
public class CategoryProductApplication {
	public static void main(String[] args) {
		SpringApplication.run(CategoryProductApplication.class, args);
	}
}
