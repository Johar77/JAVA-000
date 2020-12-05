package com.johar.geektime.shardingsherejdbc.repository;

import com.johar.geektime.shardingsherejdbc.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: UserInfoRepository
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/5 23:22
 * @Since: 1.0.0
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
}
