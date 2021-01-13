package com.johar.geektime.springkafkademo.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: Order
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/12 08:40
 * @Since: 1.0.0
 */
@Data
@ToString
public class Order implements Serializable {
    private int id;

    private String name;
}