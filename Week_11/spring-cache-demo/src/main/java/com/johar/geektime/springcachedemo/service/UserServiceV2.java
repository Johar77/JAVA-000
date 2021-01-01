package com.johar.geektime.springcachedemo.service;

import com.johar.geektime.springcachedemo.domain.UserPO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserServiceV2
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/2 00:11
 * @Since: 1.0.0
 */
@Service
public class UserServiceV2 extends UserServiceV1{

    @Override
    public UserPO save(UserPO userPO) {
        userPO.setId(UserPO.generatorID());
        return userPO;
    }

    @Override
    @Cacheable(value = "users", key = "#userId")
    public UserPO getByUserId(int userId) {
        System.out.println("getByUserId : " + userId);
        return new UserPO(userId, "getByUserId");
    }

    @Override
    @CachePut(value = "users", key = "#userPO.id")
    public UserPO update(UserPO userPO) {
        System.out.println("update : " + userPO.getId());
        return userPO;
    }

    @Override
    @CacheEvict(value = "users", key = "#userId")
    public void delete(int userId) {

    }
}