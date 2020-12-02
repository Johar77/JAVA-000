package com.johar.jeektime.springmockdata.service;

import com.johar.jeektime.springmockdata.jdbc.PreparedStatementJdbcRepository;
import com.johar.jeektime.springmockdata.utils.RandString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName: ProductInfoDataMockService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/2 08:48
 * @Since: 1.0.0
 */
@Service
@Order(2)
public class ProductInfoDataMockService implements CommandLineRunner {

    private final String category_sql = "insert into t_product_category(category_name, category_code, parent_id) values(?,?,?);";

    private final String product_info_sql = "insert into t_product_info(product_code,product_name,bar_code,category_id "
            +",price, cost,publish_status,audit_status,production_date,shelf_lift,indate,update_time) values(?,?,?,?,?,?,?,?,?,?,?,?);";

    @Autowired
    private PreparedStatementJdbcRepository repository;

    @Override
    public void run(String... args) throws Exception {
        List<Map<String, Object>> productList = repository.query("select product_id from t_product_info;");
        if (productList != null && productList.size() > 0){
            return;
        }

        int parentCategory = 100;
        int category = 1000;
        for (int i = 0; i < parentCategory; i++){
            repository.update(category_sql, getParentProductCategory());
        }

        List<Map<String, Object>> parentIds = repository.query("select category_id from t_product_category;");
        //Object userId = parentIds.get(0).get("category_id");
        Random random = new Random();
        for (int i = 0; i < category; i++){
            repository.update(category_sql, getProductCategory(parentIds.get(random.nextInt(parentIds.size())).get("category_id")));
        }

        List<Map<String, Object>> ids = repository.query("select category_id from t_product_category;");
        for (int i = 0; i< category; i++){
            repository.update(product_info_sql, getProductInfoParams(ids.get(random.nextInt(ids.size())).get("category_id")));
        }
    }

    private Object[] getProductInfoParams(Object categoryId){
        List<Object> params = new ArrayList<>();
        params.add(RandString.randCharter(8));
        params.add(RandString.randCharter(15));
        params.add(RandString.randNum(12));
        params.add(categoryId);
        Random random = new Random();
        int price = random.nextInt(10000) + 10;
        params.add(price);
        params.add(price - random.nextInt(price)+ 10);
        params.add(random.nextInt(1));
        params.add(random.nextInt(1));
        LocalDate now = LocalDate.now();
        params.add(now.minusDays(random.nextInt(300)));
        params.add(90);
        params.add(LocalDateTime.now());
        params.add(LocalDateTime.now());

        return params.toArray();
    }

    private Object[] getParentProductCategory(){
        List<Object> params = new ArrayList<>();
        params.add(RandString.randCharter(15));
        params.add(RandString.randCharter(6));
        params.add(0L);

        return params.toArray();
    }

    private Object[] getProductCategory(Object categoryId){
        List<Object> params = new ArrayList<>();
        params.add(RandString.randCharter(15));
        params.add(RandString.randCharter(6));
        params.add(categoryId);

        return params.toArray();
    }
}