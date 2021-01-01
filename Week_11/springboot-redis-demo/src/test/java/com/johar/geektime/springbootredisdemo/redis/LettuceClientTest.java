package com.johar.geektime.springbootredisdemo.redis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LettuceClientTest {

    @Autowired
    private LettuceClient lettuceClient;

    @BeforeEach
    void setUp() {
        lettuceClient.flushDB();
    }

    @AfterEach
    void tearDown() {
        lettuceClient.flushDB();
    }

    @Test
    void del() {
        String key = "delTest";
        lettuceClient.set(key, key);
        assertEquals(true, lettuceClient.exists(key));
        lettuceClient.del(key);
        assertEquals(false, lettuceClient.exists(key));
    }

    @Test
    void set() {
        String key = "setTest";
        lettuceClient.set(key, key);
        assertEquals(key, lettuceClient.get(key));
    }

    @Test
    void testSet1() throws InterruptedException {
        String key = "setTest";
        lettuceClient.set(key, key, 5);
        Thread.sleep(1000);
        assertEquals(true, lettuceClient.exists(key));
        Thread.sleep(5000);
        assertEquals(false, lettuceClient.exists(key));
    }

    @Test
    void keys() {
        String key = "keysTest";
        lettuceClient.set(key, key);
        Set set = lettuceClient.keys("*");
        assertEquals(true, set.contains(key));
    }

    @Test
    void exists() {
        String key = "existsTest";
        assertEquals(false, lettuceClient.exists(key));
        lettuceClient.set(key, key);
        assertEquals(true, lettuceClient.exists(key));
    }

    @Test
    void dbSize() {
        String key = "dbSizeTest";
        assertEquals(0, lettuceClient.dbSize());
        lettuceClient.set(key, key);
        assertEquals(1, lettuceClient.dbSize());
    }

    @Test
    void ping() {
        assertEquals("PONG", lettuceClient.ping());
    }
}