package com.johar.geektime.rusticolusrpcdemoapi.domain;

import lombok.Data;

/**
 * @ClassName: User
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/13 20:07
 * @Since: 1.0.0
 */
@Data
public class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}