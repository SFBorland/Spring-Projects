package com.seanborland.spring.mvc.kafka.service.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class ApacheKafkaConsumer {
    
    private static final String TOPIC_NAME = "temp_eb_v1";
    //private static final String BOOTSTRAP_SERVERS = "10.16.14.18:9092,10.16.14.19:9092,10.16.14.28:9092";
    private static final String BOOTSTRAP_SERVERS = "tst2-kafka-broker0.mesos.rccl.com:9092";
    private static final String STRING_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";
    private static final String BYTE_ARRAY_DESERIALIZER = "org.apache.kafka.common.serialization.ByteArrayDeserializer";
    
    public void explicitConsumerExampleUsingAutoCommit() {
        Properties consumerProperties = new Properties();
        consumerProperties.setProperty("bootstrap.servers", BOOTSTRAP_SERVERS);
        consumerProperties.setProperty("group.id", "test_group");
        consumerProperties.setProperty("key.deserializer", STRING_DESERIALIZER);
        consumerProperties.setProperty("value.deserializer", BYTE_ARRAY_DESERIALIZER);
        //consumerProperties.setProperty("enable.auto.commit", "true");
        //consumerProperties.setProperty("auto.commit.interval.ms", "1000");
        
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProperties);
        
        //Best practice to pass a list to subscribe(...) since you can't call subscribe again because it'll overwrite
        //the existing subscribe. Using the managed list you can add topics to it (ME: can I remove?)
        List<String> topics = new ArrayList<>();
        topics.add(TOPIC_NAME);
        
        consumer.subscribe(topics);
        
        while (true) {
            System.out.println("polling...");
            //time between polls, why?
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("####################### record as String: " + record.toString());
                System.out.println(record.value());//TODO: Throws an error when value.deserailizer is ByteArrayD*
            }
        }
    }
    
    /**
     * TODO: consume from a specific partition, handled by consumer group?
     */
    public void consumeFromPartion() {
    }
}
