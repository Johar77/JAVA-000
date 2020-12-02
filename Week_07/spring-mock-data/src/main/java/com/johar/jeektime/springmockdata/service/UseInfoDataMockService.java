package com.johar.jeektime.springmockdata.service;

import com.johar.jeektime.springmockdata.jdbc.PreparedStatementJdbcRepository;
import com.johar.jeektime.springmockdata.utils.RandString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @ClassName: CommonDataMockService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/12/1 08:44
 * @Since: 1.0.0
 */
@Service
@Order(1)
public class UseInfoDataMockService implements CommandLineRunner {

    @Autowired
    private PreparedStatementJdbcRepository repository;

    private final String user_login_sql = "insert into t_user_login(user_id, login_name, password, user_status) values(?,?,?,?);";

    private final String user_info_sql = "insert into t_user_info(username, card_type, card_num, phone_num,email,register_time, birthday, "
        +"membership_level,integral,balance) values(?,?,?,?,?,?,?,?,?,?)";

    private final String user_address_sql = "insert into t_user_address(user_id, recipient,phone_num,province,city,area,full_address) "
            + "values(?,?,?,?,?,?,?);";

    private final String charters = "abcdefghijklmnopqrstuvwxyz";

    @Override
    public void run(String... args) throws Exception {
        //System.out.println("模拟基础表数据 begin ------------------>");
        List<Map<String, Object>> userIdList = repository.query("select user_id from t_user_info;");
        if (userIdList != null && userIdList.size() > 0){
            return;
        }

        int num = 1000;
        String password = DigestUtils.md5DigestAsHex("admin12345".getBytes());
        int state = 0;
        String name = null;
        for (int i = 0; i < num; i++){
            name = randomName();
            repository.update(user_info_sql, getUserInfoArgs(new Object[] {name}));
            List<Map<String, Object>> userInfo = repository.query("select user_id from t_user_info where username = '" + name + "';");
            Object userId = userInfo.get(0).get("user_id");
            //System.out.println(userId);
            repository.update(user_login_sql, new Object[]{userId, name, password, state});

            repository.update(user_address_sql, getUserAddress(new Object[]{userId, name}));
        }

        //System.out.println("模拟基础表数据 end ------------------>");
    }

    private Object[] getUserAddress(Object[] args){
        List<Object> result = new ArrayList<>();
        Arrays.stream(args).forEach(arg -> {
            result.add(arg);
        });
        result.add(RandString.randNum(11));
        result.add("安徽省");
        result.add("安庆市");
        result.add("太湖县");
        result.add("新仓镇");

        return result.toArray();
    }

    private Object[] getUserInfoArgs(Object[] args){
        List<Object> result = new ArrayList<>();
        Arrays.stream(args).forEach(arg -> {
            result.add(arg);
        });
        Random random = new Random();
        result.add(1);
        result.add(RandString.randNum(18));
        result.add(RandString.randNum(11));
        result.add(randomEmail());
        result.add(LocalDateTime.now());
        result.add(LocalDate.now().minusYears(random.nextInt(20) + 10));
        result.add(random.nextInt(20));
        result.add(random.nextInt(10000));
        result.add(random.nextInt(10000000));

        return result.toArray();
    }

    private String randomEmail(){
        return randomName() + "@163.com";
    }

    private String randomName(){
        int count = charters.length();
        Random random = new Random(System.currentTimeMillis());
        int nameLength = random.nextInt(10);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nameLength + 3; i++){
            builder.append(charters.charAt(random.nextInt(count)));
        }

        builder.append(random.nextInt(9));
        builder.append(random.nextInt(9));
        builder.append(random.nextInt(9));

        return builder.toString();
    }
}