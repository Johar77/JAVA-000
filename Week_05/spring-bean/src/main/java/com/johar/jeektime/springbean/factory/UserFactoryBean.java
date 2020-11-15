package com.johar.jeektime.springbean.factory;

import com.johar.jeektime.springbean.domian.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @ClassName: UserFactoryBean
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/15 11:04
 * @Since: 1.0.0
 */
public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}