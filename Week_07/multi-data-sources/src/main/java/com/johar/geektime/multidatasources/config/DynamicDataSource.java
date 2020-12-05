package com.johar.geektime.multidatasources.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @ClassName: DynamicDataSource
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/5 21:02
 * @Since: 1.0.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource{

    private static final ThreadLocal<DataSourceType> holders = new ThreadLocal<>();

    public DynamicDataSource(DataSource defaultDataSource, Map<Object, Object> targetDataSources){
        super.setDefaultTargetDataSource(defaultDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return null;
    }

    public static void setDataSourceType(DataSourceType dataSourceType){
        holders.set(dataSourceType);
    }

    public static DataSourceType getDataSourceType(){
        return holders.get();
    }

    public static void clearDataSourceType() {
        holders.remove();
    }
}