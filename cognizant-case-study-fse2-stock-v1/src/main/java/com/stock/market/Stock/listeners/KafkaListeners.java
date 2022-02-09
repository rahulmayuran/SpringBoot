package com.stock.market.Stock.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

	@KafkaListener(topics = {"fse_stock"}, groupId = "stockGroup")
	void listener(String data) 
	{
		System.out.println("From Kafka Listener -> "+data);
	};
}
