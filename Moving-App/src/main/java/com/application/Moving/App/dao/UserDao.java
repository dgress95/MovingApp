package com.application.Moving.App.dao;

import com.application.Moving.App.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();
    User getUserById(int userId);

    User getByUsername(String username);

    User create(User user);

    boolean userExists(String username);

}
