package com.example.categoryProduct.controller;

import com.example.categoryProduct.dto.request.ProductRequest;
import com.example.categoryProduct.dto.response.ProductResponse;
import com.example.categoryProduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        ProductResponse response = service.getProductById(id);
        return ResponseEntity.ok(response);
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> responseList = service.getAll();
        return ResponseEntity.ok(responseList);
    }

    // Create a new product
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse created = service.createProduct(productRequest);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}