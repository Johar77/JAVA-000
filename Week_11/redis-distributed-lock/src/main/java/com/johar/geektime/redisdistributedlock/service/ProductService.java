package com.johar.geektime.redisdistributedlock.service;

import com.johar.geektime.redisdistributedlock.domain.Product;
import com.johar.geektime.redisdistributedlock.dto.OpCode;
import com.johar.geektime.redisdistributedlock.dto.OrderParamDto;
import com.johar.geektime.redisdistributedlock.lock.ReentrantRedisLockEx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ProductService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/2 10:49
 * @Since: 1.0.0
 */
@Service
@Slf4j
public class ProductService {

    @Autowired
    private ReentrantRedisLockEx reentrantRedisLockEx;

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String PRODUCT_KEY = "product";

    public void order(OrderParamDto paramDto){

        Product product =  (Product) redisTemplate.opsForHash().get(PRODUCT_KEY, paramDto.getProductId());
        log.info("get product from redis: {},param : {}", product, paramDto);
        if (product == null){
            if (paramDto.getType() == OpCode.Minus){
                log.error("order error: don't have the product {}", paramDto.getProductId());
                return;
            } else if (paramDto.getType() == OpCode.Add){
                product = new Product(paramDto.getProductId(), "ddd", paramDto.getNum());
            }
        } else {
            if (paramDto.getType() == OpCode.Minus){
                product.setCount(product.getCount() - paramDto.getNum());
            } else if (paramDto.getType() == OpCode.Add){
                product.setCount(product.getCount() + paramDto.getNum());
            }
        }

        redisTemplate.opsForHash().put(PRODUCT_KEY, paramDto.getProductId(), product);
    }
}