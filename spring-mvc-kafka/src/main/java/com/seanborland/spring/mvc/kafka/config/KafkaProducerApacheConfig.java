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
    private static final String STRING_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
    private static final String BYTE_ARRAY_SERIALIZER = "org.apache.kafka.common.serialization.ByteArraySerializer";
    
    @Bean
    public static Producer<String, String> apacheProducerFactory() {
        return new KafkaProducer<>(getProperties());
    }
    
    private static Properties getProperties() {
        Properties properties = new Properties();
        
        //A list of host/port pairs to use for establishing the initial connection to the Kafka cluster.
        properties.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        
        //Number of acknowledgments the producer requires the leader to receive before considering a request complete.
        properties.put("acks", "all");
        
        properties.put("key.serializer", STRING_SERIALIZER);
        
        properties.put("value.serializer", STRING_SERIALIZER);
        
        //Use ByteArraySerializer for better performance.
        //properties.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        
        return properties;
    }
}
//a
