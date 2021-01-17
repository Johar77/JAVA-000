package com.johar.geektime.kmqcore.demo;

import com.johar.geektime.kmqcore.core.KmqBroker;
import com.johar.geektime.kmqcore.core.KmqConsumer;
import com.johar.geektime.kmqcore.core.KmqMessage;
import com.johar.geektime.kmqcore.core.KmqProducer;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: MultiConsumerKmqDemo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/17 16:19
 * @Since: 1.0.0
 */
public class MultiConsumerKmqDemo {
    public static void main(String[] args) throws InterruptedException, IOException {
        String topic = "kmq.test";
        KmqBroker broker = new KmqBroker();
        broker.createTopic(topic);
        CountDownLatch countDownLatch = new CountDownLatch(1);

        KmqConsumer consumer1 = broker.createConsumer();
        consumer1.subscribe(topic);
        new Thread(() -> {
            while (countDownLatch.getCount() > 0){
                KmqMessage<Order> message = consumer1.poll(100);
                if (message != null){
                    System.out.println("consumer1: " + message.getBody());
                }
            }
            System.out.println("consumer1 程序退出。");
        }).start();


        KmqConsumer consumer2 = broker.createConsumer();
        consumer2.subscribe(topic);
        new Thread(() -> {
            while (countDownLatch.getCount() > 0){
                KmqMessage<Order> message = consumer2.poll(100);
                if (message != null){
                    System.out.println("consumer2: " + message.getBody());
                }
            }
            System.out.println("consumer2 程序退出。");
        }).start();

        KmqConsumer consumer3 = broker.createConsumer("test");
        consumer3.subscribe(topic);
        new Thread(() -> {
            while (countDownLatch.getCount() > 0){
                KmqMessage<Order> message = consumer3.poll(100);
                if (message != null){
                    System.out.println("consumer3: " + message.getBody());
                }
            }
            System.out.println("consumer3 程序退出。");
        }).start();

        KmqProducer producer = broker.createProducer();
        for (int i = 0; i < 100; i++){
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