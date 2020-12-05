package com.johar.geektime.multidatasources.controller;

import com.johar.geektime.commonlib.api.BaseResponse;
import com.johar.geektime.multidatasources.service.DbService;
import com.johar.geektime.multidatasources.service.DbServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: DbControllerV2
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/5 21:34
 * @Since: 1.0.0
 */
@RestController
@RequestMapping(path = "v2/db")
public class DbControllerV2 {

    @Autowired
    private DbServiceV2 service;

    @PostMapping(path = "/add")
    public BaseResponse add(@RequestParam("id") int id){
        service.add(id);
        return BaseResponse.Ok();
    }

    @GetMapping(path = "/find")
    public BaseResponse findAll(){
        return BaseResponse.Ok(service.findAll());
    }
}