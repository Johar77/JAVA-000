package com.johar.geektime.dubbohmilytccdemo.repository;

import com.johar.geektime.dubbohmilytccdemo.entity.FrozenPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: FrozenRepository
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/21 23:52
 * @Since: 1.0.0
 */
@Repository
public interface FrozenRepository extends JpaRepository<FrozenPO,Long> {

    void deleteAllByTransactionId(long transactionId);

    FrozenPO findFirstByTransactionId(long transactionId);
}
