package com.johar.geektime.dubbohmilytccdemo.service;


import com.johar.geektime.dubbohmilytccapi.Account;
import com.johar.geektime.dubbohmilytccapi.OrderService;
import com.johar.geektime.dubbohmilytccdemo.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: OrderService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/21 19:48
 * @Since: 1.0.0
 */
@DubboService(version = "1.0.0")
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Override
    public void transfer(Account from, long amount, Account to) {
        log.info("transfer {}-{} money from {} to {}", amount, from.getType(), from.getId(), to.getId());
    }

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public void transfer() {

    }

    @Transactional(rollbackFor = Exception.class)
    public boolean confirm(){
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean cancel(){
        return true;
    }
}