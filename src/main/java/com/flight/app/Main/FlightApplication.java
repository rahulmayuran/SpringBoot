package com.flight.app.Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class FlightApplication {

	public static void main(String[] args) {

		SpringApplication.run(FlightApplication.class, args);
	}

}
