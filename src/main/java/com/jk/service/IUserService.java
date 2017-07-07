package com.jk.service;

import com.jk.model.User;

import java.util.List;

/**
 * Created by hukai on 2016/9/9.
 */
public interface IUserService {
    User queryUser(User user);
    List<User> getAllUser4Page(User user,Integer page,Integer rows);
    boolean updateUser(User user);
    boolean saveUser(User user);
}
