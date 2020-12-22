package com.johar.geektime.dubbohmilytccdemo.repository;

import com.johar.geektime.dubbohmilytccdemo.entity.AccountPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: AccountRepository
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/21 23:52
 * @Since: 1.0.0
 */
@Repository
public interface AccountRepository extends JpaRepository<AccountPO, Long> {

    AccountPO findAccountPOByUserIdAndAccountType(Long userId, Integer accountType);

    List<AccountPO> findAllByUserId(Long userId);
}
