package com.xuyao.springboot;

import com.xuyao.springboot.annotation.TimeConsume;
import com.xuyao.springboot.bean.po.User;
import com.xuyao.springboot.startup.SpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class JdbcTests {

    @Autowired
    private DataSource dataSource;

    @Test
    @TimeConsume
    public void batchInsert() throws SQLException {
        long start = System.currentTimeMillis();
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        String sql = "insert into t_user (name, username, password, gender, create_date, phone, email, address) values ( ?,?,?, ?,?,?,?, ? )";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        List<User> users = MybatisTests.initList();
        User user;
        Date date = new Date(System.currentTimeMillis());
        for (int i = 0; i < users.size(); i++) {
            user = users.get(i);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getGender());
            preparedStatement.setDate(5, date);
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setString(7, user.getEmail());
            preparedStatement.setString(8, user.getAddress());
            preparedStatement.addBatch();
            if(i %1000 == 999){
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }
        }
        preparedStatement.executeBatch();
        preparedStatement.clearBatch();
        connection.setAutoCommit(true);
        long end = System.currentTimeMillis();
        System.out.println("cost: " + (end - start));
    }

}
