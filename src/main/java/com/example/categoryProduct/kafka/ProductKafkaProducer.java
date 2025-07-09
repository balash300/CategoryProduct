package com.example.categoryProduct.kafka;

import com.example.categoryProduct.dto.ProductCreatedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public ProductKafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendProductCreatedEvent(ProductCreatedEvent event) {
        try {
            kafkaTemplate.send("product-topic", objectMapper.writeValueAsString(event));
            System.out.println("Kafka product message sent: " + event.getName());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
