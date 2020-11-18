package com.johar.jeektime.springdb.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: BatchPrepareStatementJdbcRepository
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/18 22:56
 * @Since: 1.0.0
 */
public class BatchPrepareStatementJdbcRepository extends CommonNativeJdbcRepository{
    public void batchUpdate(String sql, List<Object[]> args) throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            args.forEach(objects -> {
                for (int i = 0; i < objects.length; i++){
                    try {
                        preparedStatement.setObject(i + 1, objects[i]);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                try {
                    preparedStatement.addBatch();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });

            connection.commit();
            preparedStatement.clearBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            release(connection);
        }

    }
}