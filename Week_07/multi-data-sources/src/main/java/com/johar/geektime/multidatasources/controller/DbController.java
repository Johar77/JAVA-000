package com.johar.geektime.multidatasources.controller;

import com.johar.geektime.commonlib.api.BaseResponse;
import com.johar.geektime.multidatasources.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: DbController
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/5 16:11
 * @Since: 1.0.0
 */
@RestController
@RequestMapping(path = "v1/db")
public class DbController {

    @Autowired
    private DbService service;

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