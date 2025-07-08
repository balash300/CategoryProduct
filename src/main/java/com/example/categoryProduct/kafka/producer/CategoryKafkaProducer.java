package com.example.categoryProduct.kafka.producer;

import com.example.categoryProduct.dto.CategoryCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CategoryKafkaProducer {

    private final KafkaTemplate<String, CategoryCreatedEvent> kafkaTemplate;

    public CategoryKafkaProducer(KafkaTemplate<String, CategoryCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCategoryCreatedEvent(CategoryCreatedEvent event) {
        kafkaTemplate.send("category-topic", event);
        System.out.println("Kafka category message sent: " + event.getName());
    }
}