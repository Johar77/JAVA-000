package com.johar.geektime.springkafkademo.kafka;


import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: KafkaConfig
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/12 23:45
 * @Since: 1.0.0
 */
@Configuration
public class KafkaConfig {

//    @Bean
//    public KafkaAdmin admin(){
//        KafkaAdmin admin = new KafkaAdmin(producerProperties());
//        admin.setFatalIfBrokerNotAvailable(true);
//        admin.setAutoCreate(true);
//        return admin;
//    }
//    @Bean
//    public NewTopic initialTopic(@Value("${spring.kafka.template.default-topic}") String topicName,
//                                 @Value("${spring.kafka.num.partitions}") int partition,
//                                 @Value("${spring.kafka.num.replication}") short replication) {
//        return new NewTopic(topicName, partition, replication);
//    }

//    public Map<String, Object> producerProperties(){
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.ACKS_CONFIG, "0");
//        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 100);
//        props.put(ProducerConfig.LINGER_MS_CONFIG, 500);
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9001,localhost:9002,localhost:9003");
//
////        props.put(ConsumerConfig.GROUP_ID_CONFIG, "1");
////        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9001,localhost:9002,localhost:9003");
////        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "1");
////        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
////        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
////        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
////        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        return props;
//    }

//    /**
//     * 不使用spring boot的KafkaAutoConfiguration默认方式创建的DefaultKafkaProducerFactory，重新定义
//     * @return
//     */
//    @Bean("produceFactory")
//    public DefaultKafkaProducerFactory produceFactory(){
//        return new DefaultKafkaProducerFactory(producerProperties());
//    }
//    /**
//     * 不使用spring boot的KafkaAutoConfiguration默认方式创建的KafkaTemplate，重新定义
//     * @param produceFactory
//     * @return
//     */
//    @Bean
//    public KafkaTemplate<Integer, String> kafkaTemplate(DefaultKafkaProducerFactory produceFactory){
//        return new KafkaTemplate<Integer, String>(produceFactory);
//    }

    //public DefaultConsu
}