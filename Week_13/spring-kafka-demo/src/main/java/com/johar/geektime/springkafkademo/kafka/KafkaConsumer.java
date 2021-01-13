package com.johar.geektime.springkafkademo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @ClassName: KafkaConsumer
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/12 23:51
 * @Since: 1.0.0
 */
@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "test.order.topic", groupId = "1")
    public void receiveMessage(ConsumerRecord<?, ?> record){
        Optional<?> kafkaMessage = Optional.ofNullable(record);
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            log.info("receive msg: {}", message);
        }
    }
}