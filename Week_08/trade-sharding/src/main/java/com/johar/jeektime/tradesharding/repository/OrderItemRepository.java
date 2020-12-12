package com.johar.jeektime.tradesharding.repository;

import com.johar.jeektime.tradesharding.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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

    @Modifying
    @Query(value = "insert into t_order_item(order_item_id, order_id) values (?1, ?2)", nativeQuery = true)
    public int insert(Long orderItemId, Long orderId);
}
