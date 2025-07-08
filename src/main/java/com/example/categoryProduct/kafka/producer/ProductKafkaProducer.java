package com.example.categoryProduct.kafka.producer;

import com.example.categoryProduct.dto.ProductCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductKafkaProducer {

    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    public ProductKafkaProducer(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendProductCreatedEvent(ProductCreatedEvent event) {
        kafkaTemplate.send("product-topic", event);
        System.out.println("Kafka product message sent: " + event.getName());
    }
}
