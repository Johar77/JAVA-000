package com.johar.geektime.rusticolusrpcdemoapi.domain;

import lombok.Data;

/**
 * @ClassName: Order
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/13 20:07
 * @Since: 1.0.0
 */
@Data
public class Order {
    private int id;

    private String name;

    private float amount;

    public Order(int id, String name, float amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }
}