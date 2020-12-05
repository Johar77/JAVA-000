package com.johar.geektime.shardingsherejdbc.controller;

import com.johar.geektime.commonlib.api.BaseResponse;
import com.johar.geektime.shardingsherejdbc.domain.UserInfo;
import com.johar.geektime.shardingsherejdbc.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/5 23:22
 * @Since: 1.0.0
 */
@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserInfoRepository repository;

    @PostMapping(path = "/add")
    public BaseResponse addUser(@RequestParam("name") String name){
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        UserInfo userInfo1 = repository.save(userInfo);
        return BaseResponse.Ok(userInfo1);
    }

    @GetMapping(path = "/find")
    public BaseResponse findAll(){
        return BaseResponse.Ok(repository.findAll());
    }
}