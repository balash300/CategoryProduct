package com.example.categoryProduct.service;

import com.example.categoryProduct.dto.response.ProductResponse;
import com.example.categoryProduct.dto.request.ProductRequest;

import java.util.List;

public interface ProductService {
    ProductResponse getProductById(Long id);
    ProductResponse createProduct(ProductRequest productRequest);
//    ProductResponse updateProduct(ProductRequest productRequest);
//    void deleteProductById(Long id);
    List<ProductResponse> getAll();
}
