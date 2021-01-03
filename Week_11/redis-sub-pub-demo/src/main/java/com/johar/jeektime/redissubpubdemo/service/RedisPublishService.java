package com.johar.jeektime.redissubpubdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @ClassName: RedisPublishservice
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/3 13:18
 * @Since: 1.0.0
 */
@Service
@Slf4j
public class RedisPublishService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    boolean send(String topic, String data){
        redisTemplate.convertAndSend(topic, data);
        return true;
    }
}