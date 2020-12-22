package com.johar.geektime.dubbohmilytccdemo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName: UserPO
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/21 21:39
 * @Since: 1.0.0
 */
@Data
@Entity
@Table(name = "t_user")
public class UserPO implements Serializable {
    private static final long serialVersionUID = 661434701950670670L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
}