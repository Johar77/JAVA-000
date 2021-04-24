package com.johar.geektime.springmybatis.controller;

import com.johar.geektime.commonlib.api.BaseResponse;
import com.johar.geektime.springmybatis.dao.dto.UserDto;
import com.johar.geektime.springmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/4/24 17:29
 * @Since: 1.0.0
 */
@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/findByName")
    public BaseResponse<UserDto> findByName(@RequestParam("name") String name){
        return userService.findByName(name);
    }

    @GetMapping(path = "/findwithPage")
    public BaseResponse findWithPage(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "minAge", required = false)  Integer minAge,
                                     @RequestParam(value = "maxAge", required = false) Integer maxAge,
                                     @RequestParam(value = "pageSize", required = true) Integer pageSize,
                                     @RequestParam(value = "pageNum", required = true) Integer pageNum){
        return userService.findWithPage(name, minAge, maxAge, pageSize, pageNum);
    }

    @PostMapping(path = "/addOne")
    public BaseResponse addOne(@RequestBody UserDto userDto){
        return userService.addOne(userDto);
    }


    @PostMapping(path = "/batchAdd")
    public BaseResponse batchAdd(@RequestBody List<UserDto> userDtoList){
        return userService.batchAdd(userDtoList);
    }
}