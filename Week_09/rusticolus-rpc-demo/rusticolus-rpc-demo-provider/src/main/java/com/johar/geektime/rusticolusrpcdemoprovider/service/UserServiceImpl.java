package com.johar.geektime.rusticolusrpcdemoprovider.service;

import com.johar.geektime.rusticolusrpcdemoapi.domain.User;
import com.johar.geektime.rusticolusrpcdemoapi.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/13 20:45
 * @Since: 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findUserById(int id) {
        return new User(id, "Johar");
    }
}