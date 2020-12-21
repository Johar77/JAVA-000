package com.johar.geektime.dubbohmilytccdemo.service;

import com.johar.geektime.dubbohmilytccapi.Account;
import com.johar.geektime.dubbohmilytccapi.AccountType;
import com.johar.geektime.dubbohmilytccapi.User;
import com.johar.geektime.dubbohmilytccapi.UserService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/21 20:10
 * @Since: 1.0.0
 */
@DubboService(version = "1.0.0")
public class UserServiceImpl implements UserService {
    @Override
    public User findById(long id) {
        User user = new User();
        user.setId(id);
        user.setName("Johar");

        Account cny = new Account();
        cny.setUserId(id);
        cny.setType(AccountType.CNY);
        cny.setAvailableAmount(100);
        cny.setFreezeAmount(100);
        cny.setTotalAmount(cny.getFreezeAmount() + cny.getAvailableAmount());

        Account usb = new Account();
        usb.setUserId(id);
        usb.setType(AccountType.USD);
        usb.setAvailableAmount(200);
        usb.setFreezeAmount(200);
        usb.setTotalAmount(usb.getFreezeAmount() + usb.getAvailableAmount());

        user.add(cny);
        user.add(usb);

        return user;
    }
}