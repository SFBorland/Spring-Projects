package com.seanborland.spring.mvc.kafka.repository.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Service
public class ApacheKafkaConsumer {
    
    private static final String TOPIC_NAME = "temp_eb_v1";
    private static final String BOOTSTRAP_SERVERS = "10.16.14.18:9092,10.16.14.19:9092,10.16.14.28:9092";
    private static final String STRING_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";
    private static final String BYTE_ARRAY_DESERIALIZER = "org.apache.kafka.common.serialization.ByteArrayDeserializer";
    
    public void explicitConsumerExampleUsingAutoCommit() {
        Properties consumerProperties = new Properties();
        consumerProperties.setProperty("bootstrap.servers", BOOTSTRAP_SERVERS);
        consumerProperties.setProperty("group.id", "test_group");
        ////consumerProperties.setProperty("enable.auto.commit", "true");
        //consumerProperties.setProperty("auto.commit.interval.ms", "1000");
        consumerProperties.setProperty("key.deserializer", STRING_DESERIALIZER);
        consumerProperties.setProperty("value.deserializer", BYTE_ARRAY_DESERIALIZER);
        
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProperties);
        consumer.subscribe(Collections.singletonList(TOPIC_NAME));
        
        while (true) {
            //time between polls
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            System.out.println("polling...");
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("####################### " + record.toString());
                
            }
        }
    }
}
