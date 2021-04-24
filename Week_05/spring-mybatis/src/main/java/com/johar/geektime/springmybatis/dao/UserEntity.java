package com.johar.geektime.springmybatis.dao;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: UserEntity
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/4/24 17:30
 * @Since: 1.0.0
 */
@Data
@ToString
public class UserEntity implements Serializable {
    @Getter(AccessLevel.NONE)
    private long serialVersionUID = 1;

    private int id;

    private String name;

    private int age;

    /**
     * 0：未知；1：男性；2：女性
     */
    private int sex;
}