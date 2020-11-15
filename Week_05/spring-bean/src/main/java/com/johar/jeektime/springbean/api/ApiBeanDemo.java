package com.johar.jeektime.springbean.api;

import com.johar.jeektime.springbean.domian.User;
import com.johar.jeektime.springbean.factory.DefaultUserFactory;
import com.johar.jeektime.springbean.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

/**
 * @ClassName: ApiBeanDemo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/15 11:45
 * @Since: 1.0.0
 */
public class ApiBeanDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        registerUserBeanDefinition(applicationContext);
        registerUserBeanDefinition(applicationContext, "user");

        applicationContext.refresh();

        createBean(applicationContext);

        System.out.println(applicationContext.getBeansOfType(User.class));

        applicationContext.close();
    }

    private static void registerUserBeanDefinition(BeanDefinitionRegistry registry){
        registerUserBeanDefinition(registry, null);
    }

    private static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", 2001);
        beanDefinitionBuilder.addPropertyValue("name", "Lynn");
        beanDefinitionBuilder.addPropertyValue("age", 21);

        if (StringUtils.hasText(beanName)){
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else{
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }


    private static void createBean(AnnotationConfigApplicationContext applicationContext){
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
        UserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.createUser());
    }
}