package com.softeer.BE.global.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissionConfig {

    @Bean
    public RedissonClient redissonClient() {
        // Redisson 클라이언트 구성
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://localhost:6379");

        // Redisson 클라이언트 생성 및 반환
        return Redisson.create(config);
    }
}