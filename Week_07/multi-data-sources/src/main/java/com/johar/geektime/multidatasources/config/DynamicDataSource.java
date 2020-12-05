package com.johar.geektime.multidatasources.config;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource{

    public static final String MASTER = "Master";
    public static final String[] SLAVES = new String[] { "slave-1", "slave-2" };

    private static final ThreadLocal<String> holders = new ThreadLocal<>();

    public DynamicDataSource(DataSource defaultDataSource, Map<Object, Object> targetDataSources){
        super.setDefaultTargetDataSource(defaultDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSourceName();
    }

    public static void setDataSourceName(String dataSourceName){
        holders.set(dataSourceName);
    }

    public static String getDataSourceName(){
        String name = holders.get();
        log.info("determineCurrentLookupKey ---->{} -----", name);
        return name;
    }

    public static void clearDataSourceType() {
        holders.remove();
    }
}