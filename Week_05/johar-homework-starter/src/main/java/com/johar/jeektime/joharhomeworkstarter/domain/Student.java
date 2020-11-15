package com.johar.jeektime.joharhomeworkstarter.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName: Student
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/15 23:48
 * @Since: 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ConfigurationProperties(prefix = "student")
public class Student {
    private int id;
    private String name;

    public void init(){
        System.out.println("hello.................");
    }

    public Student create() {
        return new Student(101, "Johar");
    }
}