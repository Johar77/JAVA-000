package com.johar.geektime.rusticolusrpcdemoconsumer;

import com.johar.geektime.rusticolusrpcdemoapi.domain.Order;
import com.johar.geektime.rusticolusrpcdemoapi.domain.User;
import com.johar.geektime.rusticolusrpcdemoapi.service.OrderService;
import com.johar.geektime.rusticolusrpcdemoapi.service.UserService;
import com.johar.jeektime.rusticolusrpcclient.core.proxy.ByteRubbyProxyRpcClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class RusticolusRpcDemoConsumerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RusticolusRpcDemoConsumerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        UserService userService = ByteRubbyProxyRpcClient.create(UserService.class, "http://localhost:8080/");
        User user = userService.findUserById(1);
        log.info("find user id = 1 from service: {}", user);

        OrderService orderService = ByteRubbyProxyRpcClient.create(OrderService.class, "http://localhost:8080/");
        Order order = orderService.findOrderById(1);
        log.info("find order id = 1 from service: {}", order);
    }
}
