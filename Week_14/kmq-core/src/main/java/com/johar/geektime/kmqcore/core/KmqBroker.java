package com.johar.geektime.kmqcore.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: KmqBroker
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/16 16:16
 * @Since: 1.0.0
 */
public final class KmqBroker {
    public static final int CAPACITY = 10000;

    private final Map<String, Kmq> kmqMap = new ConcurrentHashMap<>(64);

    public void createTopic(String topic){
        kmqMap.putIfAbsent(topic, new Kmq(topic, CAPACITY));
    }

    public Kmq findKmq(String topic){
        return kmqMap.get(topic);
    }

    public KmqProducer createProducer(){
        return new KmqProducer(this);
    }

    public KmqConsumer createConsumer(){
        return new KmqConsumer(this);
    }

    public KmqConsumer createConsumer(String topic){
        return new KmqConsumer(this, topic);
    }
}