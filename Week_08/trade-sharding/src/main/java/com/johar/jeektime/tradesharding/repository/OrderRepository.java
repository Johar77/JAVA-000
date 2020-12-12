package com.johar.jeektime.tradesharding.repository;

import com.johar.jeektime.tradesharding.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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

    @Modifying
    @Query(value = "insert into t_order(order_id, address_id, customer_id, order_sn) values (?1, ?2, ?3, ?4)", nativeQuery = true)
    int insert(Long orderId, Long addressId, Long customerId, String orderSN);
}
