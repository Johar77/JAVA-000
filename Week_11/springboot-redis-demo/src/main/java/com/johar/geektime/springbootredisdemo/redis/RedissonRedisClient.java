package com.johar.geektime.springbootredisdemo.redis;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @ClassName: RedissonClient
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/1 22:09
 * @Since: 1.0.0
 */
@Service
public class RedissonRedisClient implements RedisClient{

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public long del(String... keys) {
        return redissonClient.getKeys().delete(keys);
    }

    @Override
    public void set(byte[] key, byte[] value, long liveTime) {
        redissonClient.getBucket(new String(key, StandardCharsets.UTF_8)).set(value, liveTime, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, String value, long liveTime) {
        redissonClient.getBucket(key).set(value, liveTime, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, String value) {
        redissonClient.getBucket(key).set(value);
    }

    @Override
    public void set(byte[] key, byte[] value) {
        redissonClient.getBucket(new String(key, StandardCharsets.UTF_8)).set(value);
    }

    @Override
    public String get(String key) {
        RBucket<String> result = redissonClient.getBucket(key);
        return result.get();
    }

    @Override
    public Set keys(String pattern) {
        Set result = new LinkedHashSet<>();
        Iterable<String> keysByPattern = redissonClient.getKeys().getKeysByPattern(pattern);
        keysByPattern.forEach(key -> result.add(key));

        return result;
    }

    @Override
    public boolean exists(String key) {
        return redissonClient.getKeys().countExists(key) > 0;
    }

    @Override
    public String flushDB() {
        redissonClient.getKeys().flushdb();
        return "OK";
    }

    @Override
    public long dbSize() {
        return redissonClient.getKeys().count();
    }

    @Override
    public String ping() {
        redissonClient.getKeys().count();
        return "PONG";
    }
}