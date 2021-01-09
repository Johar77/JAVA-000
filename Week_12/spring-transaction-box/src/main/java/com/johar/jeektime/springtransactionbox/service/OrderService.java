package com.johar.jeektime.springtransactionbox.service;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.johar.jeektime.springtransactionbox.dao.OrderDao;
import com.johar.jeektime.springtransactionbox.entity.OrderEntity;
import com.johar.jeektime.springtransactionbox.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: OrderService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/9 16:24
 * @Since: 1.0.0
 */
@Service
@EnableScheduling
@Slf4j
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    private EventBus eventBus = new EventBus();

    private ConcurrentHashMap<String, List<Customer>> customerMap = new ConcurrentHashMap<>();

    public void save(OrderEntity orderEntity){
        orderDao.save(orderEntity);
    }

    public void updateStatus(OrderEntity orderEntity){
        orderDao.updateStatus(orderEntity);
    }

    public void batchUpdateStatus(List<OrderEntity> orderEntityList){
        orderDao.batchUpdateStatus(orderEntityList);
    }

    public List<OrderEntity> findAll(){
        return orderDao.findAll();
    }

    public synchronized void register(Customer customer){
        if (customerMap.containsKey(customer.getGroup())){
            List<Customer> customerList = customerMap.get(customer.getGroup());
            customerList.add(customer);
        } else {
            List<Customer> customerList = new ArrayList<>();
            customerList.add(customer);
            customerMap.put(customer.getGroup(), customerList);
        }
    }

    public synchronized void unregister(Customer customer){
        if (customerMap.containsKey(customer.getGroup())){
            List<Customer> customerList = customerMap.get(customer.getGroup());
            customerList.remove(customer);
        }
    }



    @Scheduled(fixedRate = 30)
    @Transactional(rollbackFor = Exception.class)
    public void mockData(){
        OrderEntity entity = create();
        save(entity);
    }

    @Scheduled(fixedRate = 100)
    @Transactional(rollbackFor = Exception.class)
    public void handlerData(){
        List<OrderEntity> orderEntityList = findAll();
        orderEntityList.forEach(orderEntity -> {
            eventBus.post(orderEntity);
            orderEntity.setStatus(1);
        });
        batchUpdateStatus(orderEntityList);
    }

    @PostConstruct
    public void init(){
        eventBus.register(new EventListener());
    }

    private OrderEntity create(){
        Random random = new Random(System.currentTimeMillis());
        OrderEntity entity = new OrderEntity();
        entity.setName("test");
        entity.setTotalMoney(random.nextInt(100000) + 100L);
        entity.setStatus(0);
        entity.setUserId(random.nextInt(10000) + 1L);

        return entity;
    }


}