package com.johar.jeektime.springdb.jdbc;

import com.johar.jeektime.springdb.common.PageResult;
import com.johar.jeektime.springdb.domain.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName: JdbcTemplateCommand
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/17 23:52
 * @Since: 1.0.0
 */
@Component
public class JdbcTemplateCommand implements CommandLineRunner {

    @Autowired
    private StudentJdbcRepository jdbcRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("JdbcTemplateCommand run........");

        jdbcRepository.deleteAll();

        jdbcRepository.insert(new StudentDao("Johar", 29, 1, 11));
        jdbcRepository.insert(new StudentDao("Anna", 25, 2, 10));
        jdbcRepository.insert(new StudentDao("Lynn", 24, 2, 9));
        jdbcRepository.insert(new StudentDao("Jin", 26, 2, 7));
        jdbcRepository.insert(new StudentDao("Bob", 21, 1, 5));

        PageResult<StudentDao> result = jdbcRepository.findList(10, 1, "");
        System.out.println(result);

        StudentDao studentDao = result.getData().get(0);
        studentDao.setName("HelloKit");
        jdbcRepository.update(studentDao);

        jdbcRepository.delete(result.data.get(result.data.size() - 1).getId());

        System.out.println("JdbcTemplateCommand end........");
    }
}