package com.jk.dao;

import com.jk.model.AdminMenuEntity;
import com.jk.model.AdminUsersEntity;

import java.util.List;

public interface AdminMenuDao extends BaseDao<AdminMenuEntity>{
    List<AdminMenuEntity> queryMenuList4Show(AdminUsersEntity adminUsersEntity);
    List<AdminMenuEntity> queryMenu4UpdateList(AdminMenuEntity adminMenuEntity);
    List<AdminMenuEntity> queryAllList(AdminUsersEntity adminUsersEntity);
}
