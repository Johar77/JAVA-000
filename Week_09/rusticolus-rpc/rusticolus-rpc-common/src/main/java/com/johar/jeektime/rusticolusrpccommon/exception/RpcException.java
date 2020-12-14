package com.johar.jeektime.rusticolusrpccommon.exception;

/**
 * @ClassName: RpcException
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/14 08:39
 * @Since: 1.0.0
 */
public class RpcException extends Exception{
    static final long serialVersionUID = 7818375828146090155L;

    public RpcException(){
        super();
    }

    public RpcException(String message){
        super(message);
    }

    public RpcException(String message, Throwable throwable){
        super(message, throwable);
    }

    public RpcException(Throwable throwable){
        super(throwable);
    }
}