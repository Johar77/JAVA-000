package com.johar.jeektime.nettyjeektimeweek3.gateway.common;

import lombok.Data;

/**
 * @ClassName: ProxyServerInfo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/5 00:07
 * @Since: 1.0.0
 */
@Data
public class ProxyServerInfo {
    private String backEndUrl;

    private int weight;

    public ProxyServerInfo() {
    }

    public ProxyServerInfo(String backEndUrl, int weight) {
        this.backEndUrl = backEndUrl;
        this.weight = weight;
    }
}