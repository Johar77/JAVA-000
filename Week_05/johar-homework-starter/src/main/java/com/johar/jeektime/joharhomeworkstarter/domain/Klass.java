package com.johar.jeektime.joharhomeworkstarter.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @ClassName: Klass
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/15 23:48
 * @Since: 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "klass")
public class Klass {

    List<Student> students;

    public void dong(){
        System.out.println(this.getStudents());
    }
}