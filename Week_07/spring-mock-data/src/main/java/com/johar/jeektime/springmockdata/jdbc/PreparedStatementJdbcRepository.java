package com.johar.jeektime.springmockdata.jdbc;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: PrepareStatementJdbcRepository
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/1 23:16
 * @Since: 1.0.0
 */
@Repository
public class PreparedStatementJdbcRepository extends CommonNativeJdbcRepository{
    public void update(String sql, Object[] args){
        Connection connection = getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++){
                preparedStatement.setObject(i + 1, args[i]);
            }

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            release(connection);
        }
    }

    public List query(String sql){
        Connection connection = getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return extractData(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            release(connection);
        }

        return null;
    }
}