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
public class NativeJdbcRepository extends CommonNativeJdbcRepository {

    public int update(String sql) throws SQLException {
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
}