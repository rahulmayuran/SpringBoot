//package com.zuul.kafkaController;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.zuul.kafkamodels.Book;
//
//@RestController
//@RequestMapping("kafka")
//public class BookController {
//
//	@Autowired
//    private KafkaTemplate<String, Book> kafkaTemplate;
//	
//	private static final String TOPIC = "kafka_topic_name";
//
//    @GetMapping("/publish")
//    public String publishBook() {
//
//        int id = (int)(Math.floor(Math.random()*100));
//        kafkaTemplate.send(TOPIC, new Book(id, "Java Microservices", "Mark Carl", 99));
//
//        return "Published successfully";
//    }
//
//    @GetMapping("/publish/{title}")
//    public String publishBook(@PathVariable String title) {
//
//        int id = (int)(Math.floor(Math.random()*100));
//        kafkaTemplate.send(TOPIC, new Book(id, title, "Mark Carl", 99));
//
//        return "Published successfully";
//    }
//
//}
