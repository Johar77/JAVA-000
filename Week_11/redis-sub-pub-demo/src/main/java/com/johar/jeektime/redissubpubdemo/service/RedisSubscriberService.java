package com.johar.jeektime.redissubpubdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Service;

/**
 * @ClassName: RedisSubscriberService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/3 13:17
 * @Since: 1.0.0
 */
@Service
@Slf4j
public class RedisSubscriberService extends MessageListenerAdapter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String topic = redisTemplate.getStringSerializer().deserialize(message.getChannel());
        String body = redisTemplate.getStringSerializer().deserialize(message.getBody());
        log.info("Receive message: topic:{} body:{}", topic, body);
    }
}