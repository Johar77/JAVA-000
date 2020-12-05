package com.johar.geektime.multidatasources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MultiDataSourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiDataSourcesApplication.class, args);
    }

}
