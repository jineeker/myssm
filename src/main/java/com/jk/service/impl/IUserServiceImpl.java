package com.jk.service.impl;

import com.github.pagehelper.PageHelper;
import com.jk.dao.UserDao;
import com.jk.model.User;
import com.jk.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hukai on 2016/9/9.
 */
@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    protected UserDao userDao;
    //若注入失败，查看spring-mybatis.xml中各种配置的包名是否有误，导致初始化的时候改bean未被加载


    public User queryUser(User user) {
        return userDao.getUser(user);
    }

    @Override
    public List<User> getAllUser4Page(User user,Integer page,Integer rows) {
        PageHelper.startPage(page, rows);
        List<User> list = userDao.getAllUser(user);
        return list;
    }

    @Override
    public boolean updateUser(User user) {


        if(userDao.updateUser(user)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean saveUser(User user) {
//        User user2 = new User();
//        user2.setUsersId(1);
//        user2.setUserName("事务测试");
//        userDao.saveUser(user2);
        if(userDao.saveUser(user)>0){
            return true;
        }
        return false;
    }
}
