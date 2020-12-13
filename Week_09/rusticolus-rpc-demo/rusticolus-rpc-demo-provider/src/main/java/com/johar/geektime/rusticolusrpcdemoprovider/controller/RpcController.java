package com.johar.geektime.rusticolusrpcdemoprovider.controller;

import com.johar.jeektime.rusticolusrpccommon.api.RpcReponse;
import com.johar.jeektime.rusticolusrpccommon.api.RpcRequest;
import com.johar.jeektime.rusticolusrpcserver.core.RpcServerInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: RpcController
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/13 20:50
 * @Since: 1.0.0
 */
@RestController
public class RpcController {

    @Autowired
    private RpcServerInvoker serverInvoker;

    @PostMapping("/")
    public RpcReponse invoke(@RequestBody RpcRequest request){
        return serverInvoker.invoke(request);
    }
}