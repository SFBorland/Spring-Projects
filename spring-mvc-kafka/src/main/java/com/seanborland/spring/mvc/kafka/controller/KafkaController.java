package com.seanborland.spring.mvc.kafka.controller;

import com.seanborland.spring.mvc.kafka.repository.consumer.GenericKafkaConsumer;
import com.seanborland.spring.mvc.kafka.repository.producer.SpringKafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class KafkaController {
    
    private final SpringKafkaProducer springKafkaProducer;
    private final GenericKafkaConsumer genericKafkaConsumer;
    
    public KafkaController(SpringKafkaProducer springKafkaProducer, GenericKafkaConsumer genericKafkaConsumer) {
        this.springKafkaProducer = springKafkaProducer;
        this.genericKafkaConsumer = genericKafkaConsumer;
    }
    
    @GetMapping("/springKafka/{message}")
    public void sendMessage(@PathVariable(value = "message") String message) {
        log.info("remove: kaf controller");
        springKafkaProducer.sendMessage(message);
    }
    
    @GetMapping("/springKafka/consumer")
    public void getMessage() {
        log.info("remove: kaf controller");
        genericKafkaConsumer.explicitConsumerExampleUsingAutoCommit();
    }
}
