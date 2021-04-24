package com.johar.geektime.springmybatis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.johar.geektime.commonlib.api.BaseResponse;
import com.johar.geektime.springmybatis.dao.UserEntity;
import com.johar.geektime.springmybatis.dao.dto.UserDto;
import com.johar.geektime.springmybatis.dao.repository.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName: UserService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/4/24 17:30
 * @Since: 1.0.0
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserDao userDao;

    public BaseResponse findByName(String name){
        UserEntity userEntity = userDao.findByName(name);
        if (userEntity != null) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userEntity, userDto);
            BaseResponse<UserDto> response = BaseResponse.Ok(userDto);
            return response;
        } else {
            return BaseResponse.Ok();
        }
    }

    public BaseResponse findWithPage(String name, Integer minAge, Integer maxAge, Integer pageSize, Integer pageNum){
        PageHelper.startPage(pageNum, pageSize);
        List<UserEntity> userEntityList = userDao.findWithPage(name, minAge, maxAge);

        if (CollectionUtils.isEmpty(userEntityList)){
            return BaseResponse.Ok();
        }
        List<UserDto> userDtoList = new ArrayList<>();
        userEntityList.forEach(userEntity ->  {
            UserDto dto = new UserDto();
            BeanUtils.copyProperties(userEntity, dto);
            userDtoList.add(dto);
        });
        PageInfo pageInfo = new PageInfo(userDtoList);
        return BaseResponse.Ok(pageInfo);

    }

    public BaseResponse addOne(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        userDao.insert(userEntity);
        log.info("addOne id {}", userEntity.getId());
        return BaseResponse.Ok(userEntity);
    }

    public BaseResponse batchAdd(List<UserDto> userDtoList){
        List<UserEntity> userEntityList = new ArrayList<>();
        userDtoList.forEach(userDto -> {
            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(userDto, userEntity);
            userEntityList.add(userEntity);
        });

        userDao.batchInsert(userEntityList);
        return BaseResponse.Ok(userEntityList);
    }
}