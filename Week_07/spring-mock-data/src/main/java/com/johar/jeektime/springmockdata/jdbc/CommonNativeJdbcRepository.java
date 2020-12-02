package com.johar.jeektime.springmockdata.jdbc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CommonNativeJdbcRepository
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/1 23:15
 * @Since: 1.0.0
 */
@Repository
public class CommonNativeJdbcRepository {
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driveClassName;

    protected Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;
    }

    protected void release(Connection connection){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    protected List extractData(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int cols = metaData.getColumnCount();
        List result = new ArrayList();
        while (rs.next()){
            Map rowValues = new HashMap();
            for (int i = 1; i <= cols; i++){
                rowValues.put(metaData.getColumnName(i), rs.getObject(i));
            }

            result.add(rowValues);
        }

        return result;
    }
}