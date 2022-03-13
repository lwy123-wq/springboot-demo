package com.example.dididemo.dao;

import com.example.dididemo.entity.User;
import com.example.dididemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int create(String username,String password) {
        return jdbcTemplate.update("insert into user(username,password)values (?,?)",username,password);
    }
    public User findByName(String name) {
        final User user = new User();
        String sql = "SELECT username FROM user WHERE username=?";
        jdbcTemplate.query(sql, new Object[]{name}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUsername(resultSet.getString(1));
            }
        });
        return user;
    }

    @Override
    public User findByNameAndPassword(String username, String password) {
        final User user = new User();
        String sql = "SELECT * FROM user WHERE username=? AND password=?";
        jdbcTemplate.query(sql, new Object[]{username, password}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
            }
        });
        return user;
    }

    public List<User> aa() {
        String sql = "select * from user";
        List list =jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });

        return list;
    }
}