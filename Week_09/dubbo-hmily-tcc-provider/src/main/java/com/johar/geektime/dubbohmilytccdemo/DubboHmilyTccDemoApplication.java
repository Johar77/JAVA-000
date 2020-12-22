package com.johar.geektime.dubbohmilytccdemo;

import com.johar.geektime.dubbohmilytccdemo.config.DataSourceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Import(DataSourceConfiguration.class)
public class DubboHmilyTccDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboHmilyTccDemoApplication.class, args);
    }

}
