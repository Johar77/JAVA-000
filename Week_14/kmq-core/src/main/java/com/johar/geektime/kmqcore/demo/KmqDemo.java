package com.johar.geektime.kmqcore.demo;

import com.johar.geektime.kmqcore.core.KmqBroker;
import com.johar.geektime.kmqcore.core.KmqConsumer;
import com.johar.geektime.kmqcore.core.KmqMessage;
import com.johar.geektime.kmqcore.core.KmqProducer;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: KmqDemo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/16 16:49
 * @Since: 1.0.0
 */
public class KmqDemo {
    public static void main(String[] args) throws InterruptedException, IOException {
        String topic = "kmq.test";
        KmqBroker broker = new KmqBroker();
        broker.createTopic(topic);

        KmqConsumer consumer = broker.createConsumer();
        consumer.subscribe(topic);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(() -> {
            while (countDownLatch.getCount() > 0){
                KmqMessage<Order> message = consumer.poll(100);
                if (message != null){
                    System.out.println(message.getBody());
                }
            }
            System.out.println(" 程序退出。");
        }).start();

        KmqProducer producer = broker.createProducer();
        for (int i = 0; i < 1000; i++){
            Order order = new Order(1000L + i, "order" + i);
            producer.send(topic, new KmqMessage(null, order));
        }
        Thread.sleep(1000);
        System.out.println("点击任何键，发送一条消息；点击q或e，退出程序。");
        while (true){
            char c = (char) System.in.read();
            if (c > 20){
                System.out.println(c);
                producer.send(topic, new KmqMessage(null, new Order(10000L + c, "input" + c)));
            }

            if (c == 'q' || c == 'e'){
                break;
            }
        }

        countDownLatch.countDown();
    }
}