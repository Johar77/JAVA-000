package com.johar.geektime.dubbohmilytccapi;

/**
 * @ClassName: Transfer
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/21 18:17
 * @Since: 1.0.0
 */
public interface OrderService {

    void transfer(Account from, long amount, Account to);

    void transfer();
}
