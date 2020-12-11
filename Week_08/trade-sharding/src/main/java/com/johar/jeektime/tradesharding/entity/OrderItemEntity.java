package com.johar.jeektime.tradesharding.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: OrderItem
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/11 00:08
 * @Since: 1.0.0
 */
@Entity
@Table(name = "t_order_item")
@Data
public class OrderItemEntity implements Serializable {
    private static final long serialVersionUID = 661434701950670670L;

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long orderItemId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "production_name")
    private String productionName;

    @Column(name = "product_count")
    private Integer productCount;

    @Column(name = "product_price")
    private Long productPrice;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "free_money")
    private Long freeMoney;
}