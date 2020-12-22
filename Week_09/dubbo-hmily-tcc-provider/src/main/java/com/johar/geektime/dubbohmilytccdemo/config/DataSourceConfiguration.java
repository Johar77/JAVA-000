package com.johar.geektime.dubbohmilytccdemo.config;

import com.johar.geektime.dubbohmilytccdemo.annotation.DynamicSource;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DataSourceConfiguration
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/21 22:07
 * @Since: 1.0.0
 */
@Configuration
public class DataSourceConfiguration {

    public static final String FIRST = "firstDataSource";
    public static final String SECOND = "secondDataSource";

//    @Bean(name = "firstDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource firstDataSource(){
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "secondDataSource")
//    @ConfigurationProperties(prefix = "spring.second-datasource")
//    public DataSource secondDataSource(){
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean("dynamicRoutingDataSource")
//    public DataSource dynamicRoutingDataSource() {
//        Map<Object, Object> dataSourceMap = new HashMap<>(2);
//        dataSourceMap.put(FIRST, firstDataSource());
//        dataSourceMap.put(SECOND, secondDataSource());
//
//        return new DynamicSourceContext(firstDataSource(), dataSourceMap);
//    }
//
//    @Bean
//    public LocalSessionFactoryBean entityManagerFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//
//        return sessionFactory;
//    }
//
//    @Bean
//    public DynamicDtatSOurceAnnotationInterceptor dynamicDSAnnotationInterceptor() {
//        return new DynamicDtatSOurceAnnotationInterceptor();
//    }
//
//    @Bean
//    public Advisor dynamicDSAnnotationAdvisor(DynamicDtatSOurceAnnotationInterceptor dynamicDSAnnotationInterceptor) {
//        // 定义注解切点
//        AnnotationMatchingPointcut pointcut = new AnnotationMatchingPointcut(null, DynamicSource.class);
//        DefaultPointcutAdvisor dynamicDSAnnotationAdvisor = new DefaultPointcutAdvisor();
//        dynamicDSAnnotationAdvisor.setPointcut(pointcut);
//        dynamicDSAnnotationAdvisor.setAdvice(dynamicDSAnnotationInterceptor);
//        return dynamicDSAnnotationAdvisor;
//    }
}