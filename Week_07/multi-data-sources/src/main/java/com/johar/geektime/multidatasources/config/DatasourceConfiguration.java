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
    //@Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondDataSource")
    @ConfigurationProperties(prefix = "spring.second-datasource")
    public DataSource secondDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "secondJdbcTemplate")
    public JdbcTemplate secondJdbcTemplate(@Qualifier("secondDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Primary
    public DynamicDataSource dynamicDataSource(@Qualifier("primaryDataSource") DataSource dataSource,
                                               @Qualifier("secondDataSource") DataSource secondDataSource){
        Map map = new HashMap(2);
        map.put(DataSourceType.Master, dataSource);
        map.put(DataSourceType.Slave, secondDataSource);
        return new DynamicDataSource(dataSource, map);
    }
}