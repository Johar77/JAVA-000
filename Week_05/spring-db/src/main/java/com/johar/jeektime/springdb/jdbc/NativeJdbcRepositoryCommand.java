package com.johar.jeektime.springdb.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: NativeJdbcRepositoryCommand
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/18 08:53
 * @Since: 1.0.0
 */
@Component
public class NativeJdbcRepositoryCommand implements CommandLineRunner {

    @Autowired
    private NativeJdbcRepository nativeJdbcRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("NativeJdbcRepositoryCommand run......");

        nativeJdbcRepository.update("delete from t_student");

        nativeJdbcRepository.update("insert into t_student(name, age, sex, class_no) values('NativeJdbc', 30, 0, 1)");

        List result = nativeJdbcRepository.query("select * from t_student");
        System.out.println(result);


        System.out.println("NativeJdbcRepositoryCommand end......");
    }
}