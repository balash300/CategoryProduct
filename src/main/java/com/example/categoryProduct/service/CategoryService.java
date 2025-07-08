package com.example.categoryProduct.service;

import com.example.categoryProduct.dto.response.CategoryResponse;
import com.example.categoryProduct.dto.request.CategoryRequest;

import java.util.List;

public interface CategoryService {

    CategoryResponse getCategoryById(Long id);
    CategoryResponse createCategory(CategoryRequest categoryRequest);
//    CategoryDto updateCategory(CategoryRequest categoryRequest);
//    void deleteCategoryById(Long id);
    List<CategoryResponse> getAll();
}
