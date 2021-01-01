package com.johar.geektime.springbootredisdemo.redis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisTemplateClientTest {

    @Autowired
    private RedisTemplateClient redisTemplateClient;

    @BeforeEach
    void setUp() {
        redisTemplateClient.flushDB();
    }

    @AfterEach
    void tearDown() {
        redisTemplateClient.flushDB();
    }

    @Test
    void del() {
        String key = "delTest";
        redisTemplateClient.set(key, key);
        assertEquals(true, redisTemplateClient.exists(key));
        redisTemplateClient.del(key);
        assertEquals(false, redisTemplateClient.exists(key));
    }

    @Test
    void set() {
        String key = "setTest";
        redisTemplateClient.set(key, key);
        assertEquals(key, redisTemplateClient.get(key));
    }

    @Test
    void testSet1() throws InterruptedException {
        String key = "setTest";
        redisTemplateClient.set(key, key, 5);
        Thread.sleep(1000);
        assertEquals(true, redisTemplateClient.exists(key));
        Thread.sleep(5000);
        assertEquals(false, redisTemplateClient.exists(key));
    }

    @Test
    void keys() {
        String key = "keysTest";
        redisTemplateClient.set(key, key);
        Set set = redisTemplateClient.keys("*");
        assertEquals(true, set.contains(key));
    }

    @Test
    void exists() {
        String key = "existsTest";
        assertEquals(false, redisTemplateClient.exists(key));
        redisTemplateClient.set(key, key);
        assertEquals(true, redisTemplateClient.exists(key));
    }

    @Test
    void dbSize() {
        String key = "dbSizeTest";
        assertEquals(0, redisTemplateClient.dbSize());
        redisTemplateClient.set(key, key);
        assertEquals(1, redisTemplateClient.dbSize());
    }

    @Test
    void ping() {
        assertEquals("PONG", redisTemplateClient.ping());
    }
}