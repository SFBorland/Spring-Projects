package com.seanborland.spring.mvc.kafka.controller;

import com.seanborland.spring.mvc.kafka.repository.consumer.GenericKafkaConsumer;
import com.seanborland.spring.mvc.kafka.repository.producer.GenericKafkaProducer;
import com.seanborland.spring.mvc.kafka.repository.producer.SpringKafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class KafkaController {
    
    private final SpringKafkaProducer springKafkaProducer;
    private final GenericKafkaProducer genericKafkaProducer;
    private final GenericKafkaConsumer genericKafkaConsumer;
    
    
    public KafkaController(SpringKafkaProducer springKafkaProducer,
                           GenericKafkaProducer genericKafkaProducer,
                           GenericKafkaConsumer genericKafkaConsumer) {
        this.springKafkaProducer = springKafkaProducer;
        this.genericKafkaProducer = genericKafkaProducer;
        this.genericKafkaConsumer = genericKafkaConsumer;
    }
    
    @GetMapping("/springKafka/{key}/{value}")
    public void sendMessageSpring(@PathVariable(value = "key") String key,
                                  @PathVariable(value = "value") String value) {
        springKafkaProducer.sendMessage(key, value);
    }
    
    @GetMapping("/springKafka/consumer")
    public void getMessageSpring() {
        //TODO
    }
    
    @GetMapping("/genericKafka/{key}/{value}")
    public void sendMessageGeneric(@PathVariable(value = "key") String key,
                                   @PathVariable(value = "value") String value) {
        genericKafkaProducer.explicitProducerExampleUsingStringSerializer(key, value);
    }
    
    @GetMapping("/genericKafka/consumer")
    public void getMessageGeneric() {
        genericKafkaConsumer.explicitConsumerExampleUsingAutoCommit();
    }
}
