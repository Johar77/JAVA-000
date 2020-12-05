package com.johar.geektime.shardingsherejdbc.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName: UserPO
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/5 23:17
 * @Since: 1.0.0
 */
@Table(name = "t_user")
@Entity
@Data
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;
}