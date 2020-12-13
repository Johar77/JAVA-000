package com.johar.jeektime.rusticolusrpccommon.api;

import lombok.Data;

/**
 * @ClassName: RpcRequest
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/13 16:18
 * @Since: 1.0.0
 */
@Data
public class RpcRequest {

    private String serviceClass;

    private String method;

    private Object[] params;
}