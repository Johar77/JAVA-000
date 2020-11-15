package com.johar.jeektime.springbean.xml;

import com.johar.jeektime.springbean.domian.User;
import com.johar.jeektime.springbean.factory.DefaultUserFactory;
import com.johar.jeektime.springbean.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @ClassName: XmlSpecialDemo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/15 11:19
 * @Since: 1.0.0
 */
public class XmlSpecialDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:META-INF/special-bean-context.xml");

        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
        ServiceLoader<UserFactory> serviceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);


        serviceLoader.forEach(userFactory -> {
            System.out.println(userFactory.createUser());
        });
    }
}