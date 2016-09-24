package com.jk.service;

import com.jk.model.User;

import java.util.List;

/**
 * Created by hukai on 2016/9/9.
 */
public interface UserService {
    List<User> queryAllUser(int offset, int limit);
    User queryUser(User user);
}
