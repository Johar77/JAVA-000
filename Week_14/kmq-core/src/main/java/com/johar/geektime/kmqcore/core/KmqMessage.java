package com.johar.geektime.kmqcore.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

/**
 * @ClassName: KmqMessage
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/16 16:16
 * @Since: 1.0.0
 */
@Data
@AllArgsConstructor
public class KmqMessage<T> {

    private HashMap<String, Object> headers;

    private T body;
}