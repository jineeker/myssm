package com.jk.dao;

import com.jk.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by hukai on 2016/9/9.
 */
public interface UserDao {
    User getUser(User user);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int UserId);
    List<User> getAllUser(User user);
    List<Map> getMap();
    List<User> getUserPage(@Param("offset") int offset, @Param("limit") int limit);
}
