package com.johar.jeektime.redissubpubdemo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisPublishServiceTest {

    @Autowired
    private RedisPublishService redisPublishService;

    @Test
    void send() {
        redisPublishService.send("sub-pub-test", "redis send message");
    }
}