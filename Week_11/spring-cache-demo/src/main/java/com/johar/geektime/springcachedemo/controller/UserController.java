package com.johar.geektime.springcachedemo.controller;

import com.johar.geektime.commonlib.api.BaseResponse;
import com.johar.geektime.springcachedemo.domain.UserPO;
import com.johar.geektime.springcachedemo.service.UserServiceV1;
import com.johar.geektime.springcachedemo.service.UserServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/1 23:29
 * @Since: 1.0.0
 */
@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    @Qualifier("userServiceV2")
    private UserServiceV2 userService;

    @PostMapping(path = "/add")
    public BaseResponse save(@RequestBody UserPO userPO){
        return BaseResponse.Ok(userService.save(userPO));
    }

    @GetMapping(path = "/{id}")
    public BaseResponse getUserById(@PathVariable(name = "id") Integer id){
        return BaseResponse.Ok(userService.getByUserId(id));
    }

    @PostMapping(path = "/{id}")
    public BaseResponse updateUser(@RequestBody UserPO userPO){
        userService.update(userPO);
        return BaseResponse.Ok();
    }

    @DeleteMapping(path = "/{id}")
    public BaseResponse deleteUser(@PathVariable(name = "id") Integer userId){
        userService.delete(userId);
        return BaseResponse.Ok();
    }
}