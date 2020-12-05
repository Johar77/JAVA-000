package com.johar.geektime.multidatasources.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: DatasourceConfiguration
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/5 15:53
 * @Since: 1.0.0
 */
@Configuration
public class DatasourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "second1DataSource")
    @ConfigurationProperties(prefix = "spring.slave1datasource")
    public DataSource second1DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "second2DataSource")
    @ConfigurationProperties(prefix = "spring.slave2datasource")
    public DataSource second2DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "secondJdbcTemplate")
    public JdbcTemplate secondJdbcTemplate(@Qualifier("second1DataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Primary
    public DynamicDataSource dynamicDataSource(@Qualifier("primaryDataSource") DataSource dataSource,
                                               @Qualifier("second1DataSource") DataSource second1DataSource,
                                               @Qualifier("second1DataSource") DataSource second2DataSource){
        Map map = new HashMap(3);
        map.put(DynamicDataSource.MASTER, dataSource);
        map.put(DynamicDataSource.SLAVES[0], second1DataSource);
        map.put(DynamicDataSource.SLAVES[1], second2DataSource);
        return new DynamicDataSource(dataSource, map);
    }
}