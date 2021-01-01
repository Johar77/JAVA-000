package com.johar.geektime.springbootredisdemo.redis;

import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName: LettuceClient
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/1 17:12
 * @Since: 1.0.0
 */
@Service
public class LettuceClient implements RedisClient{
    @Autowired
    private StatefulRedisConnection lettuce;

    @Override
    public long del(String... keys) {
        return lettuce.sync().del(keys);
    }

    @Override
    public void set(byte[] key, byte[] value, long liveTime) {
        lettuce.sync().set(key, value, new SetArgs().ex(liveTime));
    }

    @Override
    public void set(String key, String value, long liveTime) {
        lettuce.sync().set(key, value, new SetArgs().ex(liveTime));
    }

    @Override
    public void set(String key, String value) {
        lettuce.sync().set(key, value);
    }

    @Override
    public void set(byte[] key, byte[] value) {
        lettuce.sync().set(key, value);
    }

    @Override
    public String get(String key) {
        return (String) lettuce.sync().get(key);
    }

    @Override
    public Set keys(String pattern) {
        return (Set) lettuce.sync().keys(pattern).stream().collect(Collectors.toSet());
    }

    @Override
    public boolean exists(String key) {
        return lettuce.sync().exists(key) > 0 ? true : false;
    }

    @Override
    public String flushDB() {
        return lettuce.sync().flushdb();
    }

    @Override
    public long dbSize() {
        return lettuce.sync().dbsize();
    }

    @Override
    public String ping() {
        return lettuce.sync().ping();
    }
}