package com.seanborland.spring.mvc.kafka.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SpringKafkaProducer {
    
    private final KafkaTemplate<String, String> kafkaTemplate;
    
    @Value(value = "${message.topic.name}")
    private String topicName;
    
    public SpringKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    
    public void sendMessage(String msg) {
        kafkaTemplate.send(topicName, msg);
    }
}
