package com.johar.jeektime.joharhomeworkstarter.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @ClassName: School
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/15 23:47
 * @Since: 1.0.0
 */
@Data
public class School implements ISchool {

    @Autowired(required = true)
    Klass klass;

    @Autowired
    Student student;

    @Override
    public void ding() {
        System.out.println("Class1 have " + this.klass.getStudents().size() + " students and one is " + this.student);
    }
}