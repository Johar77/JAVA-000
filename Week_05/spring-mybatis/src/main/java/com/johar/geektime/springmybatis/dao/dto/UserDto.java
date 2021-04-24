package com.johar.geektime.springmybatis.dao.dto;

import lombok.Data;

/**
 * @ClassName: UserDto
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/4/24 17:30
 * @Since: 1.0.0
 */
@Data
public class UserDto {
    private int id;

    private String name;

    private int age;

    /**
     * 0：未知；1：男性；2：女性
     */
    private int sex;
}