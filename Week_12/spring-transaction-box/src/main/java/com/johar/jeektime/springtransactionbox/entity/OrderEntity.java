package com.johar.jeektime.springtransactionbox.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: OrderEntity
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/9 15:18
 * @Since: 1.0.0
 */
@Data
@ToString
public class OrderEntity implements Serializable {
    private Long id;
    private String name;
    private Long totalMoney;
    private Long userId;
    private Integer status;
}