package com.example.categoryProduct.kafka.consumer;

import com.example.categoryProduct.dto.CategoryCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CategoryKafkaConsumer {

    @KafkaListener(topics = "category-topic", groupId = "category-cache-demo-group")
    public void listen(CategoryCreatedEvent event) {
        System.out.println("Category Kafka message arrived: " + event.getName());
    }
}
