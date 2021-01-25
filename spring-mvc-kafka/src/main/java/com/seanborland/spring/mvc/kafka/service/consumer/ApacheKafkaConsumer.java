package com.seanborland.spring.mvc.kafka.service.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class ApacheKafkaConsumer {
    
    private static final String TOPIC_NAME = "temp_eb_v1";
    private static final String BOOTSTRAP_SERVERS = "tst2-kafka-broker0.mesos.rccl.com:9092";
    private static final String STRING_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";
    private static final String BYTE_ARRAY_DESERIALIZER = "org.apache.kafka.common.serialization.ByteArrayDeserializer";
    
    public void consumeFromAnyPartition() {
        //Best practice to pass a list to subscribe(...) since you can't call subscribe again because it'll overwrite
        //the existing subscribe. Using the managed list you can add topics to it (ME: can I remove?)
        List<String> topics = new ArrayList<>();
        topics.add(TOPIC_NAME);
        
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getProperties(STRING_DESERIALIZER,
                STRING_DESERIALIZER));
        consumer.subscribe(topics);
        
        while (true) {
            System.out.println("polling...");
            //Duration is the maximum time to block (must not be greater than Long.MAX_VALUE milliseconds)
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("*** record as String: " + record.toString() + " ***");
                System.out.println(record.value());//TODO: Throws an error when value.deserailizer is ByteArrayD*
            }
        }
    }

    public void consumeFromPartition(Integer partition) {
        List<TopicPartition> topicPartitions = new ArrayList<>();
        topicPartitions.add(new TopicPartition(TOPIC_NAME, partition));
        
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getProperties(STRING_DESERIALIZER,
                STRING_DESERIALIZER));
        consumer.assign(topicPartitions);
        
        while (true) {
            System.out.printf("polling partition %s...%n", partition);
            //time between polls, why?
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(3000));
            System.out.println("After poll...");
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("*** record as String: " + record.toString() + " ***");
                System.out.println(record.value());//TODO: Throws an error when value.deserailizer is ByteArrayD*
            }
        }
    }
    
    private Properties getProperties(String keySerializerType, String valueSerializerType) {
        Properties consumerProperties = new Properties();
        consumerProperties.setProperty("bootstrap.servers", BOOTSTRAP_SERVERS);
        consumerProperties.setProperty("group.id", "eb_group_1");
        consumerProperties.setProperty("key.deserializer", keySerializerType);
        consumerProperties.setProperty("value.deserializer", valueSerializerType);
        //consumerProperties.setProperty("enable.auto.commit", "true");
        //consumerProperties.setProperty("auto.commit.interval.ms", "1000");
        
        return consumerProperties;
    }
}
//A
//B
//C
//D
//E
