package com.johar.jeektime.springbean.annotation;

import com.johar.jeektime.springbean.domian.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: AnnotationBeanDemo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/15 12:27
 * @Since: 1.0.0
 */
@Configuration
public class AnnotationBeanDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationBeanDemo.class);
        applicationContext.refresh();

        System.out.println(applicationContext.getBeansOfType(User.class));

        applicationContext.close();
    }

    @Bean(name = "user")
    public User user(){
        return User.createUser();
    }
}