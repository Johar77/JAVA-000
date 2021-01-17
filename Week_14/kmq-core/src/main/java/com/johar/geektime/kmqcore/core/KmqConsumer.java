package com.johar.geektime.kmqcore.core;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: KmqConsumer
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/16 16:16
 * @Since: 1.0.0
 */
public class KmqConsumer<T> {

    public static final String DEFAULT_GROUP = "defaultGroup";

    private final KmqBroker broker;

    private Kmq defaultKmq;

    private final String groupId;

    public KmqConsumer(KmqBroker broker) {
        this.broker = broker;
        this.groupId = DEFAULT_GROUP;
    }

    public KmqConsumer(KmqBroker broker, String groupId){
        this.broker = broker;
        this.groupId = groupId;
    }

    public void subscribe(String topic){
        defaultKmq = this.broker.findKmq(topic);
        if (defaultKmq == null){
            throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
        }
    }

    /**
     * 从subscribe(String topic)中读取数据
     * @param timeout
     * @return
     */
    public KmqMessage<T> poll(long timeout){
        return defaultKmq.poll(groupId, timeout);
    }

    public KmqMessage<T> poll(String topic, long timeout){
        Kmq kmq = this.broker.findKmq(topic);
        return kmq.poll(groupId, timeout);
    }
}