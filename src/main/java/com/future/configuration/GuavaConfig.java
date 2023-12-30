package com.future.configuration;

import com.future.cache.UserCacheLoader;
import com.future.dto.UserDto;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class GuavaConfig {

    @Bean(name = "Guava-Bean")
    protected LoadingCache<String, UserDto> userCache(UserCacheLoader userCacheLoader){

        log.info("Created a Bean for User Caching using Google Guava");
        return CacheBuilder
                .newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .refreshAfterWrite(10, TimeUnit.MINUTES)
                .build(userCacheLoader);
    }
}
