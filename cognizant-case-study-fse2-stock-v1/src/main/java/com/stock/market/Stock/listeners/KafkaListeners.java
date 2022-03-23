package com.stock.market.Stock.listeners;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("dev")
public class KafkaListeners {

	@KafkaListener(topics = {"fse_stock"}, groupId = "stockGroup")
	void listener(ConsumerRecord data)
	{
		log.info("From Kafka Listener -> "+data.topic()+" , "+data.partition()+" , "+data.value());
	}
}
