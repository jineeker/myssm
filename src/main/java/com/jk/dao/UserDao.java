package com.jk.dao;

import com.jk.model.User;

import java.util.List;

/**
 * Created by hukai on 2016/9/9.
 */
public interface UserDao {
    User getUser(User user);
    void addUser(User user);
    int updateUser(User user);
    void deleteUser(int UserId);
    List<User> getAllUser(User user);
    int saveUser(User user);
}
