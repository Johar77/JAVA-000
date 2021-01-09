package com.johar.geektime.springactivemqdemo.config;

import org.apache.activemq.xbean.BrokerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @ClassName: ActivemqConfig
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/9 11:07
 * @Since: 1.0.0
 */
@Configuration
public class ActivemqConfig {

    @Bean
    public BrokerFactoryBean activemq() throws Exception{
        BrokerFactoryBean brokerFactoryBean = new BrokerFactoryBean();
        brokerFactoryBean.setConfig(new ClassPathResource("activemq.xml"));
        brokerFactoryBean.setStart(true);

        return brokerFactoryBean;
    }
}