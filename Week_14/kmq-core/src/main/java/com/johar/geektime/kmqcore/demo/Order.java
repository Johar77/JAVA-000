package com.johar.geektime.kmqcore.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Order
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/16 16:54
 * @Since: 1.0.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private Long id;
    private String name;
}