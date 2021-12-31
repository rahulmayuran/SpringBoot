package com.stock.market.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableCaching
@EnableSwagger2
public class UserApplication {

	@Value(value = "${environment}")
	String key;

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
