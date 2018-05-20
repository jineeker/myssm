package com.jk.service.impl;

import com.jk.dao.UserDao;
import com.jk.model.AdminUserAndRoleEntity;
import com.jk.model.AdminUsersEntity;
import com.jk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hukai on 2016/9/9.
 */
@Service
public class AdminUserServiceImpl extends BaseServiceImpl<AdminUsersEntity> implements UserService {

    @Autowired
    protected UserDao userDao;

    //删除用户与角色关系
    public boolean removeUserAndRole(AdminUserAndRoleEntity adminUserAndRoleEntity){
        if(userDao.deleteUserAndRole(adminUserAndRoleEntity)>=0){
            return true;
        }
        return false;
    }

    //绑定用户与角色关系
    public boolean updateUserAndRole(AdminUserAndRoleEntity adminUserAndRoleEntity){
        if(userDao.updateUserAndRole(adminUserAndRoleEntity)>0){
            return true;
        }
        return false;
    }

//    @Override
//    public Page getAllUser4Page(AdminUsersEntity adminUsersEntity, Integer page, Integer rows) {
//        Page resultPage = PageHelper.startPage(page, rows);
//        userDao.queryAllUser(adminUsersEntity);
//        return resultPage;
//    }
//
//    public AdminUsersEntity queryUserEntity(AdminUsersEntity adminUsersEntity) {
//        return userDao.queryUserEntity(adminUsersEntity);
//    }
//
//    @Override
//    public boolean updateUser(AdminUsersEntity adminUsersEntity) {
//
//        if(userDao.updateUser(adminUsersEntity)>0){
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean saveUser(AdminUsersEntity adminUsersEntity) {
//        if(userDao.saveUser(adminUsersEntity)>0){
//            return true;
//        }
//        return false;
//    }
}
