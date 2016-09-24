package com.jk.service.impl;

import com.jk.dao.UserDao;
import com.jk.model.User;
import com.jk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hukai on 2016/9/9.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    //若注入失败，查看spring-mybatis.xml中各种配置的包名是否有误，导致初始化的时候改bean未被加载
    protected UserDao userDao;

    public List<User> queryAllUser(int offset, int limit) {
        List<User> list = userDao.getUserPage(offset,limit);
        long aa = 0;
        int i = 0;
        while(i!=1000){
            i++;
            long startTime = System.currentTimeMillis();
            userDao.getUserPage(offset,limit);
            long endTime = System.currentTimeMillis();
            aa = aa+(endTime-startTime);
        }
        System.out.println("===平均用时q=="+ (float)aa/1000);
        return list;
    }

    public User queryUser(User user) {
        return userDao.getUser(user);
    }
}
