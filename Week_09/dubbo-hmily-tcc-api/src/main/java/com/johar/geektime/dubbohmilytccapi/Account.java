package com.johar.geektime.dubbohmilytccapi;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Account
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/21 18:23
 * @Since: 1.0.0
 */
@Data
public class Account implements Serializable {

    private long id;

    private long userId;

    private int type;

    private long freezeAmount;

    private long availableAmount;

    private long totalAmount;

    public Account() {
    }

    public Account(int type) {
        this.type = type;
    }
}