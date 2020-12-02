package com.johar.jeektime.springmockdata.jdbc;

import com.johar.jeektime.springmockdata.utils.NewInstance;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

    public List query(String sql, Object[] args){
        Connection connection = getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++){
                preparedStatement.setObject(i + 1, args[i]);
            }
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

    public <T> T findOne(String sql, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        List<Map<String, Object>> mapList = query(sql);
        if (mapList == null || mapList.size() == 0){
            return null;
        }

        return NewInstance.copyProperties(mapList.get(0), clazz);
    }

    public <T> T findOne(String sql, Object[] args, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        List<Map<String, Object>> mapList = query(sql,args);
        if (mapList == null || mapList.size() == 0){
            return null;
        }

        return NewInstance.copyProperties(mapList.get(0), clazz);
    }
}