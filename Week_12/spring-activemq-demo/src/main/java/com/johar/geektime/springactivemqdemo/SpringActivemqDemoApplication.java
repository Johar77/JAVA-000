package com.johar.geektime.springactivemqdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringActivemqDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringActivemqDemoApplication.class, args);
    }

}
