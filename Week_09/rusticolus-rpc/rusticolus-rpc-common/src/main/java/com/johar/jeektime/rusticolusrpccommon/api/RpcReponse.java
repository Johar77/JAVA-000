package com.johar.jeektime.rusticolusrpccommon.api;

import lombok.Data;

/**
 * @ClassName: RpcReponse
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/13 16:19
 * @Since: 1.0.0
 */
@Data
public class RpcReponse {
    private Object result;

    private boolean status;

    private Exception exception;
}