package com.stock.market.Company.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

	@KafkaListener(topics = {"fse_company"}, groupId = "companyGroup")
	void listener(String data) 
	{
		System.out.println("From Kafka Listener -> "+data);
	};
}
