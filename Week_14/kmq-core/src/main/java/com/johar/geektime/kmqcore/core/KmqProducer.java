package com.johar.geektime.kmqcore.core;

/**
 * @ClassName: KmqProducer
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/16 16:16
 * @Since: 1.0.0
 */

public class KmqProducer {
    private final  KmqBroker broker;

    public KmqProducer(KmqBroker kmqBroker) {
        this.broker = kmqBroker;
    }

    public boolean send(String topic, KmqMessage message){
        Kmq kmq = this.broker.findKmq(topic);
        if (kmq == null) {
            throw new RuntimeException("[Topic[" + topic + "] doesn't exist." );
        }

        return kmq.send(message);
    }
}