package com.johar.geektime.springmybatis.dao.repository;

import com.johar.geektime.springmybatis.dao.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: UserDao
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/4/24 17:33
 * @Since: 1.0.0
 */
@Mapper
public interface UserDao {

    /**
     * 单个添加用户数据
     * @param userEntity
     * @return 修改记录条数
     */
    int insert(@Param("userEntity") UserEntity userEntity);

    /**
     * 批量添加用户信息
     * @param userEntityList
     * @return
     */
    int batchInsert(@Param("userList") List<UserEntity> userEntityList);

    /**
     * 根据用户名精确查看用户
     * @param name
     * @return
     */
    UserEntity findByName(@Param("name") String name);

    /**
     * 根据用户名、年龄范围分页查询用户信息
     * @param name
     * @param minAge
     * @param maxAge
     * @return
     */
    List<UserEntity> findWithPage(@Param("name") String name, @Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge);
}
