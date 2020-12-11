package com.johar.jeektime.tradesharding;

import com.johar.jeektime.tradesharding.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class TradeShardingApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TradeShardingApplication.class, args);
    }

    @Autowired
    private OrderService orderService;

    @Override
    public void run(String... args) throws Exception {
        orderService.addMockOrder();
        orderService.fingAllOrder();
    }
}
