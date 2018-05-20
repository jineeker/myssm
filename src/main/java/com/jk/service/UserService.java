package com.jk.service;

import com.jk.model.AdminUserAndRoleEntity;
import com.jk.model.AdminUsersEntity;

public interface UserService extends BaseService<AdminUsersEntity>{

    //删除用户与角色关系
    boolean removeUserAndRole(AdminUserAndRoleEntity adminUserAndRoleEntity);
    //绑定用户与角色关系
    boolean updateUserAndRole(AdminUserAndRoleEntity adminUserAndRoleEntity);
}
