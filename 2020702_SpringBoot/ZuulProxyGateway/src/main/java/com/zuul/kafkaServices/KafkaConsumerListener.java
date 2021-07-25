//package com.zuul.kafkaServices;
//
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//import com.zuul.kafkamodels.Book;
//
//
//@Service
//public class KafkaConsumerListener {
//
//    private static final String TOPIC = "kafka_topic_name";
//
//    @KafkaListener(topics = TOPIC, groupId="group_id", containerFactory = "userKafkaListenerFactory")
//    public void consumeJson(Book book) {
//        System.out.println("Consumed JSON Message: " + book);
//    }
//    
//}