package com.johar.geektime.redisdistributedlock.controller;

import com.johar.geektime.commonlib.api.BaseResponse;
import com.johar.geektime.redisdistributedlock.dto.OrderParamDto;
import com.johar.geektime.redisdistributedlock.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: OrderController
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/2 10:32
 * @Since: 1.0.0
 */
@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/order")
    public BaseResponse order(@RequestBody @Validated OrderParamDto orderParamDto){
        productService.order(orderParamDto);
        return BaseResponse.Ok();
    }
}