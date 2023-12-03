package com.future.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.Executor;

/*
    By default, spring searches for associated thread pool def.
    Either a unique TaskExecutor Bean in the context or with an Executor named taskExecutor
    If not, then SimpleAsyncTaskExecutor can be used.
 */

@Slf4j
@Configuration
@EnableAsync
public class AsyncConfig {

    //Initialize a Thread Pool Executor with limited config
    @Bean(name = "taskExecutor")
    protected Executor taskExecutor(){

        log.info("Initializing a TaskExecutor thread");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // Thread capacity
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(100); // Means 100 threads can be executed
        executor.setThreadNamePrefix("userThread-");

        executor.initialize();
        log.info("TaskExecutor thread initialized with :{} {} {} {} {} {} {} {}", executor.getActiveCount(), executor.getThreadPoolExecutor(),
                executor.getPoolSize(), executor.getCorePoolSize(), executor.getMaxPoolSize(), executor.getKeepAliveSeconds(),
                executor.getThreadPriority(), executor.getThreadNamePrefix());
        return executor;
    }

    @Bean(name = "webClient")
    protected WebClient webClient(){
        log.info("WebClient Thread initiated with {} {} {}", Thread.currentThread(), Thread.activeCount(), Thread.interrupted());
        return WebClient.builder().build();
    }
}
