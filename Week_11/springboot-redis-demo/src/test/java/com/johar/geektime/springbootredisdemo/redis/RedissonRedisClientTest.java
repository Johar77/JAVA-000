package com.johar.geektime.springbootredisdemo.redis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedissonRedisClientTest {

    @Autowired
    private RedissonRedisClient redisClient;

    @BeforeEach
    void setUp() {
        redisClient.flushDB();
    }

    @AfterEach
    void tearDown() {
        redisClient.flushDB();
    }

    @Test
    void del() {
        String key = "delTest";
        redisClient.set(key, key);
        assertEquals(true, redisClient.exists(key));
        redisClient.del(key);
        assertEquals(false, redisClient.exists(key));
    }

    @Test
    void set() {
        String key = "setTest";
        redisClient.set(key, key);
        assertEquals(key, redisClient.get(key));
    }

    @Test
    void testSet1() throws InterruptedException {
        String key = "setTest";
        redisClient.set(key, key, 5);
        Thread.sleep(1000);
        assertEquals(true, redisClient.exists(key));
        Thread.sleep(5000);
        assertEquals(false, redisClient.exists(key));
    }

    @Test
    void keys() {
        String key = "keysTest";
        redisClient.set(key, key);
        Set set = redisClient.keys("*");
        assertEquals(true, set.contains(key));
    }

    @Test
    void exists() {
        String key = "existsTest";
        assertEquals(false, redisClient.exists(key));
        redisClient.set(key, key);
        assertEquals(true, redisClient.exists(key));
    }

    @Test
    void dbSize() {
        String key = "dbSizeTest";
        assertEquals(0, redisClient.dbSize());
        redisClient.set(key, key);
        assertEquals(1, redisClient.dbSize());
    }

    @Test
    void ping() {
        assertEquals("PONG", redisClient.ping());
    }
}