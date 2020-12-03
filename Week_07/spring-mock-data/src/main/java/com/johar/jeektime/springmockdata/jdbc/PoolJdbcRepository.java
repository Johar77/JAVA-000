package com.johar.jeektime.springmockdata.jdbc;

import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: PoolJdbcRepository
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/3 22:47
 * @Since: 1.0.0
 */
@Repository
public class PoolJdbcRepository extends CommonNativeJdbcRepository {

    @Getter
    private final int coreNum;

    @Getter
    private final int maxNum;

    private List<Connection> freePools = new ArrayList<>();
    private List<Connection> busyPools = new ArrayList<>();
    private volatile int connectionCount = 0;
    private ReentrantLock locker = new ReentrantLock(true);

    public PoolJdbcRepository(){
        int cores = Runtime.getRuntime().availableProcessors();
        this.coreNum = cores;
        this.maxNum = cores << 4;
    }

    public PoolJdbcRepository(int coreNum, int maxNum){
        this.coreNum = coreNum;
        this.maxNum = maxNum;
    }

    @Override
    protected Connection getConnection() {
        locker.lock();
        try{
            if (freePools.size() == 0){
                if (connectionCount > this.maxNum){
                    throw new IllegalArgumentException("connection pool num more than maxNum");
                }

                Connection connection = super.getConnection();
                busyPools.add(connection);
                connectionCount++;

                return connection;
            }

            Connection connection = freePools.remove(0);
            busyPools.add(connection);
            return connection;
        } finally {
            locker.unlock();
        }
    }

    @Override
    protected void release(Connection connection) {
        locker.lock();
        try{
            busyPools.remove(connection);
            freePools.add(connection);
        } finally {
            locker.unlock();
        }
    }
}