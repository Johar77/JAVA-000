package com.johar.jeektime.springmockdata.service;

import com.johar.jeektime.springmockdata.jdbc.PreparedStatementJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;

/**
 * @ClassName: ProductInfoDataMockService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/2 08:48
 * @Since: 1.0.0
 */
@Service
public class ProductInfoDataMockService implements CommandLineRunner {

    private final String category_sql = "insert into t_product_category(category_name, category_code, parent_id) values(?,?,?);";

    private final String product_info_sql = "insert into t_product_info(product_code,product_name,bar_code,category_id "
            +",price, cost,publish_status,audit_status,production_date,shelf_lift,indate,update_time) values(?,?,?,?,?,?,?,?,?,?,?,?);";

    @Autowired
    private PreparedStatementJdbcRepository repository;

    @Override
    public void run(String... args) throws Exception {

    }
}