package com.johar.geektime.redisdistributedlock.lock;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedisLock
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/2 09:15
 * @Since: 1.0.0
 */
public interface RedisLock {
    /**
     * 尝试加锁
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    boolean tryLock(String key, long timeout, TimeUnit unit);

    /**
     * 解锁操作
     * @param key
     */
    void releaseLock(String key);
}
