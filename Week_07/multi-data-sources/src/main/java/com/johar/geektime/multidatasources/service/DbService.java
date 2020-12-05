package com.johar.geektime.multidatasources.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: DbService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/5 16:16
 * @Since: 1.0.0
 */
@Service
public class DbService {

    private final String insert_sql = "insert into t1(id) values(?);";

    private final String query_sql = "select id from t1;";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("secondJdbcTemplate")
    private JdbcTemplate secondJdbcTemplate;

    public void add(int num){
        jdbcTemplate.update(insert_sql, new Object[]{num});
    }

    public List<Integer> findAll(){
        List<Integer> result = new ArrayList<>();
        secondJdbcTemplate.query(query_sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                result.add(resultSet.getInt("id"));
            }
        });

        return result;
    }
}