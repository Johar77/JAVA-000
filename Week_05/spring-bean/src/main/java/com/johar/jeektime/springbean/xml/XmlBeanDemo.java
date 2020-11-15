package com.johar.jeektime.springbean.xml;

import com.johar.jeektime.springbean.domian.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: DenpendencyLookupDemo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/14 12:13
 * @Since: 1.0.0
 */
public class XmlBeanDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-context.xml");
        lookupByName(beanFactory);

        lookupByType(beanFactory);

        lookupByObjectFactory(beanFactory);
    }

    private static void lookupByName(BeanFactory beanFactory){
        User user = beanFactory.getBean("user", User.class);
        System.out.println("lookupByName: " + user);
    }

    private static void lookupByType(BeanFactory beanFactory){
        User user = beanFactory.getBean(User.class);
        System.out.println("lookupByType: " + user);
    }

    private static void lookupByObjectFactory(BeanFactory beanFactory){
        ObjectFactory<User> userObjectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = userObjectFactory.getObject();
        System.out.println("lookupByObjectFactory:  " + user);
    }
}