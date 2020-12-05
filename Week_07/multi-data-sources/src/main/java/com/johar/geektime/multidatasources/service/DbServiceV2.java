package com.johar.geektime.multidatasources.service;

import com.johar.geektime.multidatasources.annotation.ReadOnly;
import com.johar.geektime.multidatasources.config.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: DbServiceV2
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/5 21:00
 * @Since: 1.0.0
 */
@Service
public class DbServiceV2 {
    private final String insert_sql = "insert into t1(id) values(?);";

    private final String query_sql = "select id from t1;";

    @Autowired
    private DynamicDataSource dataSource;

    public void add(int num){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(insert_sql, new Object[]{num});
    }

    @ReadOnly
    public List<Integer> findAll(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Integer> result = new ArrayList<>();
        jdbcTemplate.query(query_sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                result.add(resultSet.getInt("id"));
            }
        });

        return result;
    }
}