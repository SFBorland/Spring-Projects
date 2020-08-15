package com.seanborland.spring.mvc.kafka.controller;

import com.seanborland.spring.mvc.kafka.service.SpringKafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class KafkaController {
    
    private final SpringKafkaProducer springKafkaProducer;
    
    public KafkaController(SpringKafkaProducer springKafkaProducer) {
        this.springKafkaProducer = springKafkaProducer;
    }
    
    @GetMapping("/springKafka/{message}")
    public void sendMessage(@PathVariable(value = "message") String message) {
        log.info("remove: kaf controller");
        springKafkaProducer.sendMessage(message);
    }
}
