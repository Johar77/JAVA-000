package com.johar.jeektime.dubbohmilytccconsumer;

import com.johar.geektime.dubbohmilytccapi.*;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DubboHmilyTccConsumerApplication implements CommandLineRunner {

    @DubboReference(version = "1.0.0")
    private OrderService orderService;

    @DubboReference(version = "1.0.0")
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DubboHmilyTccConsumerApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = userService.findById(1L);
        Account from = user.getAccountList().stream().filter(account -> account.getType() == AccountType.CNY).findFirst().get();
        Account to = user.getAccountList().stream().filter(account -> account.getType() == AccountType.USD).findFirst().get();
        orderService.transfer(from, 10, to);

        orderService.transfer();
    }
}
