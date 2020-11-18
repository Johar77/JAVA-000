package com.johar.jeektime.springdb.jdbc;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @ClassName: TranNativeJdbcRepository
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/18 22:25
 * @Since: 1.0.0
 */
@Repository
public class TranNativeJdbcRepository extends NativeJdbcRepository{

    @Override
    public int update(String sql) throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);

        int rows = 0;
        try (Statement statement = connection.createStatement()) {
            rows = statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException throwables) {
            connection.rollback();
            throwables.printStackTrace();
        } finally {
            release(connection);
        }

        return rows;
    }

    @Override
    public List query(String sql) {
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