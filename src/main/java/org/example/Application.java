package org.example;

import org.example.domain.cache.CacheCollect;
import org.example.utils.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public IdWorker getIdWorker() {
        return new IdWorker();
    }

    @Bean
    public RedisUtils getRedisUtils() {
        return new RedisUtils();
    }

    @Bean
    public RamdomUtils getRamdomUtils() {
        return new RamdomUtils();
    }

    @Bean
    public MailUtils getMailUtils() {
        return new MailUtils();
    }

}
