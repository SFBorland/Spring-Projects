package com.seanborland.spring.mvc.kafka.service.producer;

import com.seanborland.spring.mvc.kafka.config.KafkaProducerApacheConfig;
import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Properties;

@Service
public class ApacheKafkaProducer {
    
    private static final String TOPIC_NAME = "temp_eb_v1";
    private static final String BOOTSTRAP_SERVERS = "tst2-kafka-broker0.mesos.rccl.com:9092";
    private static final String STRING_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
    private static final String BYTE_ARRAY_SERIALIZER = "org.apache.kafka.common.serialization.ByteArraySerializer";
    
    public void writeToAnyPartitionAsString(String key, String value) {
        Producer<String, String> producer = new KafkaProducer<>(getProperties(STRING_SERIALIZER, STRING_SERIALIZER));
        producer.send(new ProducerRecord<>(TOPIC_NAME, key, value));
        producer.close();
    }
    
    public void writeToSpecificPartitionAsString(String key, String value, Integer partition) {
        Producer<String, String> producer = new KafkaProducer<>(getProperties(STRING_SERIALIZER, STRING_SERIALIZER));
        producer.send(new ProducerRecord<>(TOPIC_NAME, partition, key, value));
        producer.close();
    }
    
    @SneakyThrows
    public void writeToAnyPartitionAsByteArray(String key, String value) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutput objectOutput = new ObjectOutputStream(byteArrayOutputStream);
        objectOutput.writeObject(value);
        
        byte[] byteValue = byteArrayOutputStream.toByteArray();
        
        Producer<String, byte[]> producer = new KafkaProducer<>(getProperties(STRING_SERIALIZER, BYTE_ARRAY_SERIALIZER));
        producer.send(new ProducerRecord<>(TOPIC_NAME, key, byteValue));
        producer.close();
    }

    @SneakyThrows
    public void writeToSpecificPartitionAsByteArray(String key, String value, Integer partition) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutput objectOutput = new ObjectOutputStream(byteArrayOutputStream);
        objectOutput.writeObject(value);
    
        byte[] byteValue = byteArrayOutputStream.toByteArray();
    
        Producer<String, byte[]> producer = new KafkaProducer<>(getProperties(STRING_SERIALIZER, BYTE_ARRAY_SERIALIZER));
        producer.send(new ProducerRecord<>(TOPIC_NAME, partition, key, byteValue));
        producer.close();
    }
    
    /**
     * Example using Apache Producer but properties are managed by Spring.
     */
    public void springManagedProducerConfigExample(String key, String value) {
        Producer<String, String> producer = KafkaProducerApacheConfig.apacheProducerFactory();
        producer.send(new ProducerRecord<>(TOPIC_NAME, "message2", "sample apache client 2"));
        producer.close();
    }
    
    private Properties getProperties(String keySerializerType, String valueSerializerType) {
        Properties producerProperties = new Properties();
        producerProperties.setProperty("bootstrap.servers", BOOTSTRAP_SERVERS);
        producerProperties.setProperty("acks", "all");
        producerProperties.setProperty("key.serializer", keySerializerType);
        producerProperties.setProperty("value.serializer", valueSerializerType);
        
        return producerProperties;
    }
}
