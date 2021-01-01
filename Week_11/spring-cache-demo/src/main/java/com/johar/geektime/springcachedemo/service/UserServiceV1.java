package com.johar.geektime.springcachedemo.service;

import com.johar.geektime.springcachedemo.domain.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;

/**
 * @ClassName: UserService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/1 23:43
 * @Since: 1.0.0
 */
@Service
public class UserServiceV1 {

    private static final String USER_KEY = "USERS";

    @Resource
    private RedisTemplate redisTemplate;

    public UserPO save(UserPO userPO){
        userPO.setId(UserPO.generatorID());

        redisTemplate.opsForHash().put(USER_KEY, userPO.getId(), userPO);

        return userPO;
    }

    public UserPO getByUserId(int userId){
        return (UserPO)redisTemplate.opsForHash().get(USER_KEY, userId);
    }

    public UserPO update(UserPO userPO){
        redisTemplate.opsForHash().put(USER_KEY, userPO.getId(), userPO);
        return userPO;
    }

    public void delete(int userId){
        redisTemplate.opsForHash().delete(USER_KEY, userId);
    }
}