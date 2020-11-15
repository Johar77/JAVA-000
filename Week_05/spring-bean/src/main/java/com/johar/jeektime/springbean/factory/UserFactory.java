package com.johar.jeektime.springbean.factory;

import com.johar.jeektime.springbean.domian.User;

/**
 * @ClassName: UserFactory
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/15 11:03
 * @Since: 1.0.0
 */
public interface UserFactory {

    default User createUser() {
        return User.createUser();
    }
}
