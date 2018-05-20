package com.jk.dao;

import com.jk.model.AdminUserAndRoleEntity;
import com.jk.model.AdminUsersEntity;

public interface UserDao extends BaseDao<AdminUsersEntity>{
    int deleteUserAndRole(AdminUserAndRoleEntity adminUserAndRoleEntity);
    int updateUserAndRole(AdminUserAndRoleEntity adminUserAndRoleEntity);
}
