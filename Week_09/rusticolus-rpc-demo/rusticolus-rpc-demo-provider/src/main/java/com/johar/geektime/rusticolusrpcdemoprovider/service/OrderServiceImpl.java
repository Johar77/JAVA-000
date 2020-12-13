package com.johar.geektime.rusticolusrpcdemoprovider.service;

import com.johar.geektime.rusticolusrpcdemoapi.domain.Order;
import com.johar.geektime.rusticolusrpcdemoapi.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: OrderServiceImpl
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/13 20:45
 * @Since: 1.0.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Order findOrderById(int id) {
        return new Order(id, "Johar", 5000_000.0f);
    }
}