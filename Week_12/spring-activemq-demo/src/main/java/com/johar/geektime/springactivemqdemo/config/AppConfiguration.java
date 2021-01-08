package com.johar.geektime.springactivemqdemo.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

/**
 * @ClassName: AppConfiguration
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/9 00:00
 * @Since: 1.0.0
 */
@Configuration
public class AppConfiguration {

    @Value("${spring.activemq.broker-url}")
    private String host;

    @Bean(name = "connectionFactory")
    public ConnectionFactory getActiveMqConnection(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(host);
        connectionFactory.setUserName("admin");
        connectionFactory.setPassword("admin");
        return connectionFactory;
    }

    @Bean(name="queueListenerContainerFactory")
    public JmsListenerContainerFactory queueListenerContailerFactory(@Qualifier("connectionFactory") ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(false);
        return factory;
    }
    @Bean(name="topicListenerContainerFactory")
    public JmsListenerContainerFactory topicListenerContainerFactory(@Qualifier("connectionFactory") ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }
}