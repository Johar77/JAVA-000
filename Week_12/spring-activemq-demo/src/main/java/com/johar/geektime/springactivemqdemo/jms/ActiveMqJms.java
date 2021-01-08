package com.johar.geektime.springactivemqdemo.jms;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: ActiveMqJms
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/8 23:02
 * @Since: 1.0.0
 */
//@Component
public class ActiveMqJms implements CommandLineRunner {

    public static void testDestination(Destination destination, String brokerURL){
        try{
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(brokerURL);
            ActiveMQConnection connection = (ActiveMQConnection) activeMQConnectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            final AtomicInteger count = new AtomicInteger(0);
            MessageConsumer consumer = session.createConsumer(destination);
            MessageListener listener = new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    System.out.println(count.incrementAndGet() + "=> receive from " +
                            destination.toString() + ", content: " + message);
                }
            };

            consumer.setMessageListener(listener);

            MessageProducer producer = session.createProducer(destination);
            int index = 0;
            while (index < 100){
                TextMessage message = session.createTextMessage(index + " message.");
                producer.send(message);
                index++;
            }

            Thread.sleep(2000);
            session.close();
            connection.close();
        } catch (Exception e){

        }
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("topic begin:");
        String url = "tcp://127.0.0.1:61616";
        Destination topic = new ActiveMQTopic("test.topic");
        testDestination(topic, url);
        System.out.println("topic end:");

        System.out.println("queue begin:");
        Destination queue = new ActiveMQQueue("test.queue");
        testDestination(queue, url);
        System.out.println("queue end.");
    }
}