package com.seanborland.spring.mvc.kafka.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KafkaProducerApacheConfig {
    
    private static final String BOOTSTRAP_SERVERS = "10.16.14.18:9092,10.16.14.19:9092,10.16.14.28:9092";
    
    @Bean
    public static Producer<String, String> apacheProducerFactory() {
        return new KafkaProducer<>(properties());
    }
    
    private static Properties properties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        properties.put("acks", "all");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        
        return properties;
    }
}
