package com.example.categoryProduct.service.impl;

import com.example.categoryProduct.dto.CategoryCreatedEvent;
import com.example.categoryProduct.dto.response.CategoryResponse;
import com.example.categoryProduct.dto.request.CategoryRequest;
import com.example.categoryProduct.kafka.CategoryKafkaProducer;
import com.example.categoryProduct.mapper.CategoryMapper;
import com.example.categoryProduct.model.Category;
import com.example.categoryProduct.repository.CategoryRepository;
import com.example.categoryProduct.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryKafkaProducer producer;
    private final CategoryMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository, CategoryKafkaProducer producer, CategoryMapper mapper) {
        this.repository = repository;
        this.producer = producer;
        this.mapper = mapper;
    }

    @Override
    @Cacheable(value = "categories", key = "#id")
    public CategoryResponse getCategoryById(Long id) {
        return mapper.toResponse(repository.findById(id).orElseThrow(()
                -> new RuntimeException("Category not found")));
    }

    @Override
    @Transactional
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category saved = repository.save(mapper.toEntity(categoryRequest));

        CategoryCreatedEvent event = new CategoryCreatedEvent(saved.getId(), saved.getName());
        try {
            producer.sendCategoryCreatedEvent(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return mapper.toResponse(saved);
    }

    @Override
    public List<CategoryResponse> getAll() {
        return mapper.responseList(repository.findAll());
    }
}
