package com.johar.geektime.dubbohmilytccdemo.repository;

import com.johar.geektime.dubbohmilytccdemo.entity.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: UserRepository
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/21 23:53
 * @Since: 1.0.0
 */
@Repository
public interface UserRepository extends JpaRepository<UserPO, Long> {
}
