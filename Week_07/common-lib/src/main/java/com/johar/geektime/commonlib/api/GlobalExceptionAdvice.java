package com.johar.geektime.commonlib.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ClassName: GloabExceptionAdvice
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/4/24 18:30
 * @Since: 1.0.0
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public BaseResponse exceptionHandler(Exception e){
      log.error("API interface error: ", e);
      return BaseResponse.builder().code(ResultCode.INTERNAL_SERVER_ERROR.getCode()).message(e.getMessage()).build();
    }
}