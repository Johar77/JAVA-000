package com.johar.geektime.xa2pcsharding.service;

import com.johar.geektime.xa2pcsharding.entity.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

/**
 * @ClassName: OrderService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/12 15:21
 * @Since: 1.0.0
 */
@Service
//@Slf4j
public class OrderService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String charters = "abcdefghijklmnopqrstuvwxyz";

    @Transactional(rollbackOn = SQLException.class)
    @ShardingTransactionType(TransactionType.XA)
    public TransactionType insert(final int beginIndex){
        //log.info("insert");
        return jdbcTemplate.execute("insert into t_order(order_id, address_id, customer_id, order_sn) values (?, ?, ?, ?)",
                (PreparedStatementCallback<TransactionType>) preparedStatement -> {
            doInsert(beginIndex, preparedStatement);
            return TransactionTypeHolder.get();
        });
    }

    @Transactional(rollbackOn = SQLException.class)
    @ShardingTransactionType(TransactionType.XA)
    public void insertFailed(final int beginIndex){
        //log.info("insertFailed");
        jdbcTemplate.execute("insert into t_order(order_id, address_id, customer_id, order_sn) values (?, ?, ?, ?)",
                (PreparedStatementCallback<TransactionType>) preparedStatement -> {
                    doInsert(beginIndex, preparedStatement);
                    throw new SQLException("mock data failed");
                });
    }


    private void doInsert(final int beginIndex, final PreparedStatement preparedStatement) throws SQLException {
        Random random = new Random();
        for (int i = beginIndex; i < beginIndex + 32; i++){
            preparedStatement.setObject(1, (long) (i));
            preparedStatement.setObject(2, randPositiveNumber());
            preparedStatement.setObject(3, randPositiveNumber());
            preparedStatement.setObject(4, randCharter(15));

            preparedStatement.executeUpdate();
        }
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