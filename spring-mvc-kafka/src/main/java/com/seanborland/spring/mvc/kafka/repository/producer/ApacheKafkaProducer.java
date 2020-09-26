package com.seanborland.spring.mvc.kafka.repository.producer;

import com.seanborland.spring.mvc.kafka.config.KafkaProducerApacheConfig;
import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Properties;

@Repository
public class ApacheKafkaProducer {
    
    private static final String TOPIC_NAME = "temp_eb_v1";
    private static final String BOOTSTRAP_SERVERS = "10.16.14.18:9092,10.16.14.19:9092,10.16.14.28:9092";
    private static final String STRING_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
    private static final String BYTE_ARRAY_SERIALIZER = "org.apache.kafka.common.serialization.ByteArraySerializer";
    
    public void springManagedProducerConfigExample(String key, String value) {
        Producer<String, String> producer = KafkaProducerApacheConfig.apacheProducerFactory();
        producer.send(new ProducerRecord<>(TOPIC_NAME, "message2", "sample apache client 2"));
        producer.close();
    }
    
    public void explicitProducerExampleUsingStringSerializer(String key, String value) {
        Properties producerProperties = new Properties();
        producerProperties.setProperty("bootstrap.servers", BOOTSTRAP_SERVERS);
        producerProperties.setProperty("acks", "all");
        producerProperties.setProperty("key.serializer", STRING_SERIALIZER);
        producerProperties.setProperty("value.serializer", STRING_SERIALIZER);
        
        Producer<String, String> producer = new KafkaProducer<>(producerProperties);
        producer.send(new ProducerRecord<>(TOPIC_NAME, key, value));
        producer.close();
    }
    
    @SneakyThrows
    public void explicitProducerExampleUsingByteArraySerializer(String key, String value) {
        Properties producerProperties = new Properties();
        producerProperties.setProperty("bootstrap.servers", BOOTSTRAP_SERVERS);
        producerProperties.setProperty("acks", "all");
        producerProperties.setProperty("key.serializer", STRING_SERIALIZER);
        producerProperties.setProperty("value.serializer", BYTE_ARRAY_SERIALIZER);
        
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutput objectOutput = new ObjectOutputStream(byteArrayOutputStream);
        objectOutput.writeObject(value);
        
        byte[] byteValue = byteArrayOutputStream.toByteArray();
        
        Producer<String, byte[]> producer = new KafkaProducer<>(producerProperties);
        producer.send(new ProducerRecord<>(TOPIC_NAME, key, byteValue));
        producer.close();
    }
}
