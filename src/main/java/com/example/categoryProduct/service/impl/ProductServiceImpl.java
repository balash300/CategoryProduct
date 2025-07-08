package com.example.categoryProduct.service.impl;

import com.example.categoryProduct.dto.ProductCreatedEvent;
import com.example.categoryProduct.dto.response.ProductResponse;
import com.example.categoryProduct.dto.request.ProductRequest;
import com.example.categoryProduct.kafka.producer.ProductKafkaProducer;
import com.example.categoryProduct.mapper.ProductMapper;
import com.example.categoryProduct.model.Product;
import com.example.categoryProduct.repository.ProductRepository;
import com.example.categoryProduct.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductKafkaProducer producer;
    private final ProductMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, ProductKafkaProducer producer, ProductMapper mapper) {
        this.repository = repository;
        this.producer = producer;
        this.mapper = mapper;
    }

    @Override
    @Cacheable(value = "products", key = "#id")
    public ProductResponse getProductById(Long id) {
        return mapper.toResponse(repository.findById(id).orElseThrow(()
                -> new RuntimeException("Product not found")));
    }

    @Override
    @Transactional
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product saved = repository.save(mapper.toEntity(productRequest));

        ProductCreatedEvent event = new ProductCreatedEvent(productRequest.getId(), productRequest.getName(), productRequest.getPrice());
        producer.sendProductCreatedEvent(event);

        return mapper.toResponse(saved);
    }

//    @Override
//    @Transactional
//    public ProductResponse updateProduct(ProductRequest productRequest) {
//        if (repository.findById(productRequest.getId()).isEmpty()) {
//            throw new RuntimeException("Product not found");
//        } else {
//            Product updated = repository.save(mapper.toEntity(productRequest));
//            return mapper.toResponse(updated);
//        }
//        return null;
//    }

    @Override
    public List<ProductResponse> getAll() {
        return mapper.responseList(repository.findAll());
    }
}
