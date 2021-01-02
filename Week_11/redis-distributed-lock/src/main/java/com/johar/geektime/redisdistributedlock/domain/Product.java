package com.johar.geektime.redisdistributedlock.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: Product
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/2 10:23
 * @Since: 1.0.0
 */
@Data
@ToString
public class Product implements Serializable {

    private int id;

    private String name;

    private int count;

    public Product() {
    }

    public Product(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public Product(int id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }
}