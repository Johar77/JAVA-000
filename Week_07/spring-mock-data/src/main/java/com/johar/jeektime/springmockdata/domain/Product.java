package com.johar.jeektime.springmockdata.domain;

import lombok.Data;

import java.math.BigInteger;

/**
 * @ClassName: ProductVO
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/3 00:20
 * @Since: 1.0.0
 */
@Data
public class Product {
    private BigInteger product_id;

    private String product_name;

    private BigInteger price;

    private float weight;
}