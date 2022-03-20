package com.stock.market.User.Listener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("dev")
public class KafkaListeners {

	@KafkaListener(topics = {"fse_user"}, groupId = "userGroup")
	void listener(String data)
	{
		log.info("From Kafka Listener -> "+data);
	};
}
