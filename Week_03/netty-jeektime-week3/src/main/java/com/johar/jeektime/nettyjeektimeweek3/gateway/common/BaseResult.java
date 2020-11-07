package com.johar.jeektime.nettyjeektimeweek3.gateway.common;

import lombok.Data;

/**
 * @ClassName: BaseResult
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/8 00:16
 * @Since: 1.0.0
 */
@Data
public class BaseResult<T> {
    private int resultCode;

    public String message;

    private T data;

    public BaseResult(int resultCode, String message, T data) {
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }

    public BaseResult(T data) {
        this(0, "success", data);
    }

    public BaseResult() {
        this(null);
    }
}