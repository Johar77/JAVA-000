package com.johar.geektime.kmqcore.core;

import com.johar.geektime.kmqcore.core.queue.MultiRingQueue;
import com.johar.geektime.kmqcore.core.queue.RingQueue;
import lombok.SneakyThrows;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: Kmq
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/16 16:15
 * @Since: 1.0.0
 */
public final class Kmq {

    /**
     * key：groupId
     * value: readIndex
     */
    private final ConcurrentHashMap<String, AtomicInteger> readIndexMap = new ConcurrentHashMap<>(16);

    public Kmq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        this.queue = new MultiRingQueue<>(capacity);
    }

    public String getTopic() {
        return topic;
    }

    private final String topic;

    private final int capacity;

    //private final LinkedBlockingDeque<KmqMessage> queue;
    //private final RingQueue<KmqMessage> queue;
    private final MultiRingQueue<KmqMessage> queue;

    @SneakyThrows
    public boolean send(KmqMessage message){
        return queue.offer(message);
    }

    @SneakyThrows
    public KmqMessage poll(String groupId){
        readIndexMap.putIfAbsent(groupId, new AtomicInteger(0));
        AtomicInteger value = readIndexMap.get(groupId);
        synchronized (value) {
            KmqMessage result = queue.get(value.get());
            if (result != null) {
                value.incrementAndGet();
            }

            return result;
        }
    }

    @SneakyThrows
    public KmqMessage poll(String groupId, long timeout, TimeUnit timeUnit){
        readIndexMap.putIfAbsent(groupId, new AtomicInteger(0));
        AtomicInteger value = readIndexMap.get(groupId);
        synchronized (value) {
            KmqMessage result = queue.get(value.get(), timeout, timeUnit);
            if (result != null) {
                value.incrementAndGet();
            }

            return result;
        }
    }

    public KmqMessage poll(String groupId, long timeout){
        return this.poll(groupId, timeout, TimeUnit.MILLISECONDS);
    }
}