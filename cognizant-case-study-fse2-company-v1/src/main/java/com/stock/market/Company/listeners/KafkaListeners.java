package com.stock.market.Company.listeners;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

	@KafkaListener(topics = {"fse_company"}, groupId = "companyGroup")
	void listener(ConsumerRecord data)
	{
        System.out.println("From Kafka Listener -> "+data.topic()+" , "+data.partition()+" , "+data.value());
	};
}
