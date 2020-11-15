package com.johar.jeektime.springbean.xml;

import com.johar.jeektime.springbean.domian.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jws.soap.SOAPBinding;

/**
 * @ClassName: XmlFactoryInstantiationDemo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/15 10:53
 * @Since: 1.0.0
 */
public class XmlFactoryInstantiationDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/factory-bean-context.xml");

        createByStaticMethod(beanFactory);

        createByInstanceMethod(beanFactory);

        createByFactoryBean(beanFactory);
    }


    private static void createByStaticMethod(BeanFactory beanFactory){
        User user = beanFactory.getBean("user-by-static-method", User.class);
        System.out.println("createByStaticMethod: " + user);
    }

    private static void createByInstanceMethod(BeanFactory beanFactory){
        User user = beanFactory.getBean("user-by-instance-method", User.class);
        System.out.println("createByInstanceMethod: " + user);
    }

    private static void createByFactoryBean(BeanFactory beanFactory){
        User user = beanFactory.getBean("user-by-factory-bean", User.class);
        System.out.println("createByFactoryBean: " + user);
    }
}