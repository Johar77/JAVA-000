package com.johar.jeektime.nettyjeektimeweek3.gateway.filter.request;

import java.util.Comparator;

/**
 * @ClassName: HttpRequestFilterComparator
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/4 23:35
 * @Since: 1.0.0
 */
public class HttpRequestFilterComparator implements Comparator<IHttpRequestFilter> {
    @Override
    public int compare(IHttpRequestFilter o1, IHttpRequestFilter o2) {
        if (o1.getOrderId() < o2.getOrderId()){
            return 1;
        } else if (o1.getOrderId() > o2.getOrderId()){
            return -1;
        } else {
            return 0;
        }
    }
}