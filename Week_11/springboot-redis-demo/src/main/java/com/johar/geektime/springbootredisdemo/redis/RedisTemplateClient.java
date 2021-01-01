package com.johar.geektime.springbootredisdemo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedisTemplateClient
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/1 11:41
 * @Since: 1.0.0
 */
@Service
public class RedisTemplateClient implements RedisClient{

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public long del(String... keys) {
        byte[][] keysBytes = new byte[keys.length][];
        int i = 0;
        for (String key : keys){
            keysBytes[i] = key.getBytes(StandardCharsets.UTF_8);
            i++;
        }
        return (Long) redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.del(keysBytes);
            }
        });
    }

    @Override
    public void set(byte[] key, byte[] value, long liveTime) {
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });
    }

    @Override
    public void set(String key, String value, long liveTime) {
        set(key.getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8), liveTime);
    }

    @Override
    public void set(String key, String value) {
        set(key.getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8), 0L);
    }

    @Override
    public void set(byte[] key, byte[] value) {
        set(key, value, 0L);
    }

    @Override
    public Set keys(String pattern) {
        //return redisTemplate.keys(pattern);
        Set<byte[]> set =  (Set) redisTemplate.execute(new RedisCallback<Set>() {
            @Override
            public Set doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.keys(pattern.getBytes(StandardCharsets.UTF_8));
            }
        });

        Set<String> result = new LinkedHashSet<>();
        set.forEach(bytes -> result.add(new String(bytes, StandardCharsets.UTF_8)));

        return result;
    }

    @Override
    public boolean exists(String key) {
        return (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.exists(key.getBytes(StandardCharsets.UTF_8));
            }
        });
    }

    @Override
    public long dbSize() {
        return (Long) redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.dbSize();
            }
        });
    }

    @Override
    public String ping() {
        return (String) redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.ping();
            }
        });
    }

    @Override
    public String get(String key) {
        return (String) redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return new String(redisConnection.get(key.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
            }
        });
    }

    @Override
    public String flushDB(){
        return (String) redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }
}