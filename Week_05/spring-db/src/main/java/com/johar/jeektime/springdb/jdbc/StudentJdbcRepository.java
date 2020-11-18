package com.johar.jeektime.springdb.jdbc;

import com.johar.jeektime.springdb.common.PageResult;
import com.johar.jeektime.springdb.domain.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: StudentRepository
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/17 22:55
 * @Since: 1.0.0
 */
@Repository
public class StudentJdbcRepository {

    private static final String INSERT_STUDENT_SQL = "insert into t_student(name, age, sex, class_no) values(?,?,?,?)";

    private static final String DELETE_STUDENT_SQL = "delete from t_student where id = ";

    private static final String DELETE_ALL_SQL = "delete from t_student";

    //private static final String QUERY_STUDENT_SQL = "select * from t_student where name like %?% limit ?, ?";

    //private static final String UPDATE_STUDENT_SQL = "update t_student ";

    private static final String QUERY_ROW_COUNT = "select count(id) from t_student";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(StudentDao studentDao){
        jdbcTemplate.update(INSERT_STUDENT_SQL, studentDao.getName(), studentDao.getAge(), studentDao.getSex(), studentDao.getClassNo());
    }

    public void delete(long id){

        jdbcTemplate.execute(DELETE_STUDENT_SQL + id);
    }

    public void deleteAll(){
        jdbcTemplate.execute(DELETE_ALL_SQL);
    }

    public PageResult<StudentDao> findList(int pageSize, int pageNum, String name){
        Integer sum = jdbcTemplate.queryForObject(QUERY_ROW_COUNT, Integer.class);
        int startIndex = (pageNum - 1) * pageSize;
        if (startIndex >= sum){
            throw new IllegalArgumentException("bounded out of range");
        }
        StringBuilder builder = new StringBuilder();
        builder.append("select id, name, age, sex, class_no from t_student ");
        if (StringUtils.hasText(name)){
            builder.append(" name like %" + name + "% ");
        }

        builder.append("limit " + startIndex + ", " + pageSize);

        List<StudentDao> data = new ArrayList<>();
        jdbcTemplate.query(builder.toString(), new RowCountCallbackHandler() {
            @Override
            protected void processRow(ResultSet rs, int rowNum) throws SQLException {
                StudentDao studentDao = new StudentDao();
                studentDao.setId(rs.getLong(1));
                studentDao.setName(rs.getString(2));
                studentDao.setAge(rs.getInt(3));
                studentDao.setSex(rs.getInt(4));
                studentDao.setClassNo(rs.getInt(5));
                data.add(studentDao);
            }
        });
        PageResult<StudentDao> result = new PageResult<>();
        result.setData(data);
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        result.setTotalNum(sum);

        return result;
    }

    public void update(StudentDao studentDao){
        StringBuilder builder = new StringBuilder();
        builder.append("update t_student ");
        builder.append(" set name = '" + studentDao.getName() + "'");
        builder.append(" ,age = " + studentDao.getAge());
        builder.append(" ,sex = " + studentDao.getSex());
        builder.append(" where id = " + studentDao.getId());

        jdbcTemplate.update(builder.toString());
    }
}