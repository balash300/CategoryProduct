package com.example.categoryProduct.kafka.consumer;

import com.example.categoryProduct.dto.ProductCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProductKafkaConsumer {

    @KafkaListener(topics = "product-topic", groupId = "product-cache-demo-group")
    public void listen(ProductCreatedEvent event) {
        System.out.println("Product Kafka message arrived: " + event.getName());
    }
}