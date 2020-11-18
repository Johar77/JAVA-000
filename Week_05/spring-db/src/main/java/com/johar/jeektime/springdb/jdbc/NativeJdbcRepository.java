package com.johar.jeektime.springdb.jdbc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: NativeJdbcRepository
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/18 08:23
 * @Since: 1.0.0
 */
@Repository
public class NativeJdbcRepository {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driveClassName;

    public int update(String sql){
        Connection connection = getConnection();

        int rows = 0;
        try (Statement statement = connection.createStatement()) {
            rows = statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            release(connection);
        }

        return rows;
    }

    public List query(String sql){
        List result = null;
        Connection connection = getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            result = extractData(resultSet);
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            release(connection);
        }

        return result;
    }

    private List extractData(ResultSet rs) throws SQLException {
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

    private Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;
    }

    private void release(Connection connection){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}