package com.johar.geektime.rusticolusrpcdemoapi.service;

import com.johar.geektime.rusticolusrpcdemoapi.domain.Order;

/**
 * @ClassName: OrderService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/13 20:08
 * @Since: 1.0.0
 */
public interface OrderService {
    Order findOrderById(int id);
}
