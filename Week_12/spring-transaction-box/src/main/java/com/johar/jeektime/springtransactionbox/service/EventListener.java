package com.johar.jeektime.springtransactionbox.service;

import com.google.common.eventbus.Subscribe;
import com.johar.jeektime.springtransactionbox.entity.OrderEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: EventListener
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/9 18:45
 * @Since: 1.0.0
 */
@Slf4j
public class EventListener {
    @Subscribe
    public void listen1(OrderEntity orderEntity){
        log.info("listen1 receive: {}", orderEntity);
    }

    @Subscribe
    public void listen2(OrderEntity orderEntity){
        log.info("listen2 receive: {}", orderEntity);
    }
}