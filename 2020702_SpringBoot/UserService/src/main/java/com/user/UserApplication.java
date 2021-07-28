package com.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaClient
@ComponentScan(basePackages = { "com.user"})
@EnableCaching
@EnableSwagger2
public class UserApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	   return new WebMvcConfigurerAdapter() {
	      @Override
	      public void addCorsMappings(CorsRegistry registry) {
	         registry.addMapping("/").allowedOrigins("http://localhost:4200");
	      }  
	   };
	}

	@Value(value = "${Environment}")
	String key;


	@Override
	public void run(String... args) throws Exception {
		System.out.println("Environment Checker : "+key);
	}

}
