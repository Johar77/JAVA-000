package com.johar.geektime.springbootredisdemo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import javax.naming.OperationNotSupportedException;
import java.util.Set;

/**
 * @ClassName: JedisClientClient
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/1 14:02
 * @Since: 1.0.0
 */
@Service
public class JedisClient implements RedisClient{

    @Autowired
    private Jedis jedis;

    @Override
    public void set(String key, String value) {
        jedis.set(key, value);
    }

    @Override
    public String get(String key) {
        return jedis.get(key);
    }

    @Override
    public String flushDB(){
        return jedis.flushDB();
    }

    @Override
    public long del(String... keys) {
        return jedis.del(keys);
    }

    @Override
    public void set(byte[] key, byte[] value, long liveTime) {
        jedis.set(key, value, new SetParams().ex((int)liveTime));
    }

    @Override
    public void set(String key, String value, long liveTime) {
        jedis.set(key, value, new SetParams().ex((int)liveTime));
    }

    @Override
    public void set(byte[] key, byte[] value) {
        jedis.set(key, value);
    }

    @Override
    public Set keys(String pattern) {
        return jedis.keys(pattern);
    }

    @Override
    public boolean exists(String key) {
        return jedis.exists(key);
    }

    @Override
    public long dbSize() {
        return jedis.dbSize();
    }

    @Override
    public String ping() {
        return jedis.ping();
    }
}