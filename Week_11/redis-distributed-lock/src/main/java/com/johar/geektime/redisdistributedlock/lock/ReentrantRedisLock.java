package com.johar.geektime.redisdistributedlock.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ReentrantRedisLock
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/2 09:26
 * @Since: 1.0.0
 */
@Component
public class ReentrantRedisLock implements RedisLock{

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private ThreadLocal<Integer> threadLocalCount = new ThreadLocal<>();

    @Override
    public boolean tryLock(String key, long timeout, TimeUnit unit) {
        boolean hasLocked = false;
        if (threadLocal.get() == null){
            String uuid = UUID.randomUUID().toString();
            threadLocal.set(uuid);
            hasLocked = stringRedisTemplate.opsForValue().setIfAbsent(key, uuid, timeout, unit);
        } else {
            hasLocked = true;
        }

        // 重入次数加1
        if (hasLocked){
            Integer count = threadLocalCount.get() == null ? 0 : threadLocalCount.get();
            threadLocalCount.set(count++);
        }

        return hasLocked;
    }

    @Override
    public void releaseLock(String key) {
        try {
            if (threadLocal.get().equals(stringRedisTemplate.opsForValue().get(key))){
                Integer count = threadLocalCount.get();
                if (count == null || --count <= 0){
                    stringRedisTemplate.delete(key);
                    threadLocalCount.remove();
                }
            }
        } finally {
            threadLocal.remove();
        }

    }
}