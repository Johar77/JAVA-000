package com.johar.jeektime.springtransactionbox.dao;

import com.johar.jeektime.springtransactionbox.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: OrderDao
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/9 15:19
 * @Since: 1.0.0
 */
@Mapper
public interface OrderDao {

    List<OrderEntity> findAll();

    void save(@Param("orderEntity") OrderEntity orderEntity);

    void updateStatus(@Param("orderEntity") OrderEntity orderEntity);

    void delete(@Param("id") Long id);

    void batchUpdateStatus(@Param("orderEntities") List<OrderEntity> orderEntities);
}
