package com.johar.jeektime.springdb.domain;

import lombok.Data;

/**
 * @ClassName: StudentDao
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/16 23:42
 * @Since: 1.0.0
 */
@Data
public class StudentDao {

    private long id;

    private String name;

    /**
     * 0:未知；1-男；2-女;-1:全部
     */
    private int sex;

    /**
     * -1:全部
     */
    private int age;

    /**
     * -1:全部
     */
    private int classNo;

    public StudentDao() {
    }

    public StudentDao(String name, int sex, int age, int classNo) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.classNo = classNo;
    }
}