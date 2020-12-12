package com.johar.jeektime.tradesharding.service;

import com.johar.jeektime.tradesharding.entity.OrderEntity;
import com.johar.jeektime.tradesharding.entity.OrderItemEntity;
import com.johar.jeektime.tradesharding.repository.OrderItemRepository;
import com.johar.jeektime.tradesharding.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName: OrderService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/11 00:42
 * @Since: 1.0.0
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    private static final String charters = "abcdefghijklmnopqrstuvwxyz";


    @Transactional(rollbackOn = Exception.class)
    public void addMockOrder(){
        int count = 33;
        Random random = new Random();
        for (int i = 1; i < count; i++){
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderId((long) (i));
            orderEntity.setOrderSN(randNum(20));
            int price = random.nextInt(10000) + 10;
            orderEntity.setOrderMoney(randPositiveNumber());
            orderEntity.setAddressId(randPositiveNumber());
            orderEntity.setCustomerId((long)(i & 16));
            orderEntity.setDistrictMoney(orderEntity.getOrderMoney() >>> 3);
            orderEntity.setPaymentMethod(random.nextInt(5));
            orderEntity.setPaymentMoney(orderEntity.getOrderMoney() - orderEntity.getDistrictMoney());

            orderRepository.insert(orderEntity.getOrderId(), orderEntity.getAddressId(), orderEntity.getCustomerId(), orderEntity.getOrderSN());

            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setOrderItemId((long)i);
            orderItemEntity.setOrderId(orderEntity.getOrderId());
            orderItemEntity.setFreeMoney(randPositiveNumber());
            orderItemEntity.setProductCount(1);
            orderItemEntity.setWeight(random.nextFloat());
            orderItemEntity.setProductionName(randCharter(10));
            orderItemEntity.setProductPrice(randPositiveNumber());
            orderItemEntity.setFreeMoney(randPositiveNumber());
            orderItemRepository.insert(orderItemEntity.getOrderItemId(), orderEntity.getOrderId());
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public void fingAllOrder(){
        List<OrderEntity> result = orderRepository.findAll();
//        result.forEach((orderEntity -> {
//            if (orderEntity.getOrderItemList() == null){
//                orderEntity.setOrderItemList(new ArrayList<>());
//            }
//
//            //OrderItemEntity itemEntity = orderItemRepository.findByOrderId(orderEntity.getOrderId());
//        }));
    }

    private Long randPositiveNumber(){
        Random random = new Random();
        Integer result = random.nextInt(Integer.MAX_VALUE);
        return result.longValue();
    }


    private String randNum(int length){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);
        stringBuilder.append(random.nextInt(8) + 1);
        for (int i = 0; i < length - 1; i++){
            stringBuilder.append(random.nextInt(9));
        }

        return stringBuilder.toString();
    }

    private String randCharter(int length){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++){
            stringBuilder.append(charters.charAt(random.nextInt(charters.length())));
        }

        return stringBuilder.toString();
    }
}