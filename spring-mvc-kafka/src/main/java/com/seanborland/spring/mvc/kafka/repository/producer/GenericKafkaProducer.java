package com.seanborland.spring.mvc.kafka.repository.producer;

import com.seanborland.spring.mvc.kafka.config.KafkaProducerApacheConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.springframework.stereotype.Repository;

import java.util.Properties;

@Repository
public class GenericKafkaProducer {
    
    private static final String TOPIC_NAME = "ncp_sdbe_v1";
    private static final String BOOTSTRAP_SERVERS = "10.16.14.18:9092,10.16.14.19:9092,10.16.14.28:9092";
    
    @Test
    public void springManagedProducerConfigExample() {
        Producer<String, String> producer = KafkaProducerApacheConfig.apacheProducerFactory();
        producer.send(new ProducerRecord<>(TOPIC_NAME, "sample apache client 2"));
        producer.close();
    }
    
    @Test
    public void explicitProducerExample() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        properties.put("acks", "all");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        
        Producer<String, String> producer = new KafkaProducer<>(properties);
        producer.send(new ProducerRecord<>(TOPIC_NAME, "message1","my-sample-message"));
        producer.close();
    }
}
