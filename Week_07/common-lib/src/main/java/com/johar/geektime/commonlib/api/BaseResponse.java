package com.johar.geektime.commonlib.api;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName: BaseResponse
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/5 16:35
 * @Since: 1.0.0
 */
@Data
@Builder
public class BaseResponse<T> {
    @Builder.Default
    private int code = ResultCode.SUCCESS.getCode();

    @Builder.Default
    private String message = ResultCode.SUCCESS.getMsg();

    private T data;

    public boolean isSuccess(){
        return code == ResultCode.SUCCESS.getCode();
    }

    public static BaseResponse Ok(){
        return BaseResponse.builder().code(ResultCode.SUCCESS.getCode()).message(ResultCode.SUCCESS.getMsg()).build();
    }

    public static <T> BaseResponse Ok(T data){
        return BaseResponse.builder().data(data).code(ResultCode.SUCCESS.getCode()).message(ResultCode.SUCCESS.getMsg()).build();
    }

    public BaseResponse(){
        this(ResultCode.SUCCESS, null);
    }

    public BaseResponse(ResultCode resultCode, T data){
        this.setCode(resultCode.getCode());
        this.setMessage(resultCode.getMsg());
        this.setData(data);
    }

    public BaseResponse(int resultCode, String message, T data){
        this.setCode(resultCode);
        this.setMessage(message);
        this.setData(data);
    }
}