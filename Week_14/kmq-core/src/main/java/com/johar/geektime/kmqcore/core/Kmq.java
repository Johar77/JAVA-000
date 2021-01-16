package com.johar.geektime.kmqcore.core;

import com.johar.geektime.kmqcore.core.queue.RingQueue;
import lombok.SneakyThrows;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: Kmq
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/16 16:15
 * @Since: 1.0.0
 */
public final class Kmq {

    public Kmq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        this.queue = new RingQueue<>(capacity);
    }

    private final String topic;

    private final int capacity;

    //private final LinkedBlockingDeque<KmqMessage> queue;
    private final RingQueue<KmqMessage> queue;

    @SneakyThrows
    public boolean send(KmqMessage message){
        return queue.offer(message);
    }

    @SneakyThrows
    public KmqMessage poll(){
        return queue.poll();
    }

    @SneakyThrows
    public KmqMessage poll(long timeout, TimeUnit timeUnit){
        return queue.poll(timeout, timeUnit);
    }

    public KmqMessage poll(long timeout){
        return this.poll(timeout, TimeUnit.MILLISECONDS);
    }
}