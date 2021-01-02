package com.johar.geektime.redisdistributedlock.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ReentrantRedisLockEx
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/2 09:45
 * @Since: 1.0.0
 */
@Component
@Slf4j
public class ReentrantRedisLockEx implements RedisLock{

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<>();

    @Override
    public boolean tryLock(String key, long timeout, TimeUnit unit) {
        boolean hasLocked = false;
        if (threadLocal.get() == null){
            String uuid = UUID.randomUUID().toString();
            threadLocal.set(uuid);
            hasLocked = stringRedisTemplate.opsForValue().setIfAbsent(key, uuid, timeout, unit);
            if (!hasLocked){
                for (;;){
                    hasLocked = stringRedisTemplate.opsForValue().setIfAbsent(key, uuid, timeout, unit);
                    if (hasLocked){
                        break;
                    }
                }
            }
        } else {
            hasLocked = true;
            // 其他线程重入的时候，更新超时时间
            stringRedisTemplate.expire(key, timeout, unit);
        }

        if (hasLocked){
            Integer count = integerThreadLocal.get() == null ? 0 : integerThreadLocal.get();
            integerThreadLocal.set(count++);
            log.info("Reentrant Redis Lock: {} - {}", key, count);
        }

        return hasLocked;
    }

    @Override
    public void releaseLock(String key) {
        try {
            String uuid = threadLocal.get();
            if (uuid.equals(stringRedisTemplate.opsForValue().get(key))){
                Integer count = integerThreadLocal.get();
                if (count == null || --count <= 0){
                    stringRedisTemplate.delete(key);
                    integerThreadLocal.remove();
                }
            }
        } finally {
            threadLocal.remove();
        }
    }
}