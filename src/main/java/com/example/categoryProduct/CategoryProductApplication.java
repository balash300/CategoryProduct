package com.example.categoryProduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.categoryProduct.mapper")
public class CategoryProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoryProductApplication.class, args);
	}

}
