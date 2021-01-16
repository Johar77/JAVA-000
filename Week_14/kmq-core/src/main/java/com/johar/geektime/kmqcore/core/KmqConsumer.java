package com.johar.geektime.kmqcore.core;

/**
 * @ClassName: KmqConsumer
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/16 16:16
 * @Since: 1.0.0
 */
public class KmqConsumer<T> {

    private final KmqBroker broker;

    private Kmq defaultKmq;

    public KmqConsumer(KmqBroker broker) {
        this.broker = broker;
    }

    public void subscribe(String topic){
        defaultKmq = this.broker.findKmq(topic);
        if (defaultKmq == null){
            throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
        }
    }

    public KmqMessage<T> poll(long timeout){
        return defaultKmq.poll(timeout);
    }

    public KmqMessage<T> poll(String topic, long timeout){
        Kmq kmq = this.broker.findKmq(topic);
        if (kmq == null){
            throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
        }
        return kmq.poll(timeout);
    }
}