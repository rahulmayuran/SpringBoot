package com.user.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//@Configuration
public class UserConfig {
//
/**
 * Rest Templates can be used to send/receive data between other services
 * but the condition is ,All the services must be up and Running. 
 * Kafka can help to avoid this drawback.
 */
//	@Bean
//	@LoadBalanced
//	public RestTemplate getRestTemplate(){
//        return new RestTemplate();
//    }
}
