package com.johar.geektime.dubbohmilytccapi;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: User
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/21 18:22
 * @Since: 1.0.0
 */
@Data
public class User implements Serializable {
    private Long id;
    private String name;
    private List<Account> accountList;

    public User() {
        accountList = new ArrayList<>();
    }

    public void add(Account account){
        this.accountList.add(account);
    }
}