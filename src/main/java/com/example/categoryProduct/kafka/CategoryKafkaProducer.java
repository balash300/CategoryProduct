package com.example.categoryProduct.kafka;

import com.example.categoryProduct.dto.CategoryCreatedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CategoryKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public CategoryKafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendCategoryCreatedEvent(CategoryCreatedEvent event) throws JsonProcessingException {
        try {
            kafkaTemplate.send("category-topic", objectMapper.writeValueAsString(event));
            System.out.println("Kafka category message sent: " + event.getName());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}