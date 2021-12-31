package com.stock.market.Company;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableCaching
@EnableMongoRepositories
public class CompanyApplication implements CommandLineRunner {

	@Value(value = "${environment}")
	String key;

	public static void main(String[] args) {
		SpringApplication.run(CompanyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Profiles Active : "+key);
	}
}
