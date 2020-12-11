package com.johar.jeektime.tradesharding.repository;

import com.johar.jeektime.tradesharding.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: OrderRepository
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/11 00:40
 * @Since: 1.0.0
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
