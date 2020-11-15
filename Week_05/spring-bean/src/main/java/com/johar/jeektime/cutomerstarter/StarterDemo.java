package com.johar.jeektime.cutomerstarter;

import com.johar.jeektime.joharhomeworkstarter.EnableHomeworkConfiguration;
import com.johar.jeektime.joharhomeworkstarter.domain.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName: StarterDemo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/16 00:06
 * @Since: 1.0.0
 */
@SpringBootApplication
@EnableHomeworkConfiguration
public class StarterDemo implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(StarterDemo.class, args);
    }

    @Autowired
    private School school;

    @Override
    public void run(String... args) throws Exception {
        school.ding();
    }
}