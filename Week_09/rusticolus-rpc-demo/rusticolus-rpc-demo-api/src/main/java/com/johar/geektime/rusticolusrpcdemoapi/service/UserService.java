package com.johar.geektime.rusticolusrpcdemoapi.service;

import com.johar.geektime.rusticolusrpcdemoapi.domain.User;

/**
 * @ClassName: UserService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/13 20:08
 * @Since: 1.0.0
 */
public interface UserService {
    User findUserById(int id);
}
