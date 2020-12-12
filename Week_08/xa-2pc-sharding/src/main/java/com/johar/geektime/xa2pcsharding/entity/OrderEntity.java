package com.johar.geektime.xa2pcsharding.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: OrderEntiry
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/12 15:21
 * @Since: 1.0.0
 */
@Entity
@Table(name = "t_order")
@Data
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 661434701950670670L;

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long orderId;

    @Column(unique = true, name = "order_sn", nullable = true)
    private String orderSN;

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "payment_method")
    private Integer paymentMethod;

    @Column(name = "order_money")
    private Long orderMoney;

    @Column(name = "district_money")
    private Long districtMoney;

    @Column(name = "payment_money")
    private Long paymentMoney;

    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;

    @Transient
    private List<OrderItemEntity> orderItemList;
}