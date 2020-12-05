package com.johar.geektime.multidatasources.config;

import org.springframework.context.annotation.Primary;

/**
 * @ClassName: DataSourceType
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/5 21:05
 * @Since: 1.0.0
 */
public enum DataSourceType {
    /**
     * 主
     */
    Master,

    /**
     * 备
     */
    Slave
}
