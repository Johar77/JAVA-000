package com.johar.geektime.kmqcore.core.queue;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: KmqQueue
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/16 17:22
 * @Since: 1.0.0
 */
public interface KmqQueue<T> {

    /**
     * Removes and returns first element, or null if empty.
     * Thread safe
     * @return
     */
    T poll() throws InterruptedException;

    /**
     * Removes and returns first element, or null if empty.
     * Thread safe
     * timeout
     * @param timeout
     * @param timeUnit
     * @return
     */
    T poll(long timeout, TimeUnit timeUnit) throws InterruptedException;

    /**
     * Links item as last element, or returns false if full.
     * Thread safe
     * @param item
     * @return
     */
    boolean offer(T item) throws InterruptedException;
}
