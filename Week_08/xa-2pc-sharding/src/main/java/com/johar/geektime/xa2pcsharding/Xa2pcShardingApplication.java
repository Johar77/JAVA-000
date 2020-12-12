package com.johar.geektime.xa2pcsharding;

import com.johar.geektime.xa2pcsharding.configuration.TransactionConfig;
import com.johar.geektime.xa2pcsharding.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(TransactionConfig.class)
public class Xa2pcShardingApplication implements CommandLineRunner {

    @Autowired
    private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(Xa2pcShardingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        orderService.insert(1);
        orderService.insertFailed(33);
    }
}
