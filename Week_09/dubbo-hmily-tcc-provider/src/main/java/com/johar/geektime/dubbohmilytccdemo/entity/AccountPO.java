package com.johar.geektime.dubbohmilytccdemo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName: AccountPO
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/21 21:43
 * @Since: 1.0.0
 */
@Data
@Entity
@Table(name = "t_account")
public class AccountPO implements Serializable {
    private static final long serialVersionUID = 661434701950670670L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "account_type", nullable = false)
    private Integer accountType;

    @Column(name = "freeze_amount", nullable = false)
    private Long freezeAmount;

    @Column(name = "available_amount")
    private Long availableAmount;

    @Column(name = "total_amount")
    private Long totalAmount;
}