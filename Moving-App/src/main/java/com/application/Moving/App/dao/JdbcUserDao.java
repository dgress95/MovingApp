package com.application.Moving.App.dao;

import com.application.Moving.App.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from app_user;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }
        return users;
    }

    @Override
    public User getUserById(int userId) {
        String sql = "select user_id, first_name, last_name, username, password from app_user " +
                "where user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            return mapRowToUser(results);
        }
        return null;
    }

    @Override
    public User getByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        for (User user : this.getAll()) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User create(User user) {
        String insertUserSql = "insert into app_user (first_name, last_name, username, password) " +
                "values (?, ?, ?, ?) " +
                "returning user_id;";
        int userId = jdbcTemplate.queryForObject(insertUserSql, int.class,
                user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(),
                user.getUserId());
        return getUserById(userId);
    }

    @Override
    public boolean userExists(String username) {
        int userCount = jdbcTemplate.queryForObject("select count(*) from app_user where username = '?';", int.class, username);
        return (userCount == 1);
    }

    private User mapRowToUser(SqlRowSet rowSet) {
        User user = new User();
        user.setUserId(rowSet.getInt("user_id"));
        user.setFirstName(rowSet.getString("first_name"));
        user.setLastName(rowSet.getString("last_name"));
        user.setUsername(rowSet.getString("username"));
        user.setPassword(rowSet.getString("password"));
        return user;
    }
}
