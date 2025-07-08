package com.example.categoryProduct.mapper;

import com.example.categoryProduct.dto.request.ProductRequest;
import com.example.categoryProduct.dto.response.ProductResponse;
import com.example.categoryProduct.model.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ProductMapper {
    ProductResponse toResponse(Product product);
    Product toEntity(ProductRequest request);
    List<ProductResponse> responseList(List<Product> list);
}
