package com.seanborland.spring.mvc.kafka.controller;

import com.seanborland.spring.mvc.kafka.service.consumer.ApacheKafkaConsumer;
import com.seanborland.spring.mvc.kafka.service.producer.ApacheKafkaProducer;
import com.seanborland.spring.mvc.kafka.service.producer.SpringKafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class KafkaController {
    
    private final SpringKafkaProducer springKafkaProducer;
    private final ApacheKafkaProducer apacheKafkaProducer;
    private final ApacheKafkaConsumer apacheKafkaConsumer;
    
    
    public KafkaController(SpringKafkaProducer springKafkaProducer,
                           ApacheKafkaProducer apacheKafkaProducer,
                           ApacheKafkaConsumer apacheKafkaConsumer) {
        this.springKafkaProducer = springKafkaProducer;
        this.apacheKafkaProducer = apacheKafkaProducer;
        this.apacheKafkaConsumer = apacheKafkaConsumer;
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
    
    @GetMapping("/apacheKafka/producer")
    public void sendMessageGeneric(@RequestParam(value = "key") String key,
                                   @RequestParam(value = "value") String value) {
        apacheKafkaProducer.explicitProducerExampleUsingStringSerializer(key, value);
    }
    
    @GetMapping("/apacheKafka/consumer")
    public void getMessageGeneric() {
        apacheKafkaConsumer.explicitConsumerExampleUsingAutoCommit();
    }
}
