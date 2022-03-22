package com.stock.market.User;

import com.stock.market.User.Config.Timer;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserApplication implements CommandLineRunner {

    @Value(value = "${environment}")
    String key;

    public static void main(String[] args) throws InterruptedException{
        SpringApplication.run(UserApplication.class, args);
        Timer timer = new Timer();
        timer.log();
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Profiles Active : " + key);
    }
}
