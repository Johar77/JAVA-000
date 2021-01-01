package com.johar.geektime.springbootredisdemo.redis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JedisClientClientTest {

    @Autowired
    private JedisClient client;

    @BeforeEach
    void setUp() {
        client.flushDB();
    }

    @AfterEach
    void tearDown() {
        client.flushDB();
    }

    @Test
    void del() {
        String key = "delTest";
        client.set(key, key);
        assertEquals(true, client.exists(key));
        client.del(key);
        assertEquals(false, client.exists(key));
    }

    @Test
    void set() {
        String key = "setTest";
        client.set(key, key);
        assertEquals(key, client.get(key));
    }

    @Test
    void testSet1() throws InterruptedException {
        String key = "setTest";
        client.set(key, key, 5);
        Thread.sleep(1000);
        assertEquals(true, client.exists(key));
        Thread.sleep(5000);
        assertEquals(false, client.exists(key));
    }

    @Test
    void keys() {
        String key = "keysTest";
        client.set(key, key);
        Set set = client.keys("*");
        assertEquals(true, set.contains(key));
    }

    @Test
    void exists() {
        String key = "existsTest";
        assertEquals(false, client.exists(key));
        client.set(key, key);
        assertEquals(true, client.exists(key));
    }

    @Test
    void dbSize() {
        String key = "dbSizeTest";
        assertEquals(0, client.dbSize());
        client.set(key, key);
        assertEquals(1, client.dbSize());
    }

    @Test
    void ping() {
        assertEquals("PONG", client.ping());
    }
}