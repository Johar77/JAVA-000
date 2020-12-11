package com.johar.jeektime.tradesharding.repository;

import com.johar.jeektime.tradesharding.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: OrderItemRepository
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/11 00:40
 * @Since: 1.0.0
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
}
