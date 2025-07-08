package com.example.categoryProduct.mapper;

import com.example.categoryProduct.dto.request.CategoryRequest;
import com.example.categoryProduct.dto.response.CategoryResponse;
import com.example.categoryProduct.model.Category;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface CategoryMapper {
    CategoryResponse toResponse(Category category);
    Category toEntity(CategoryRequest request);
    List<CategoryResponse> responseList(List<Category> list);
}
