package com.jk.service;

import com.jk.model.AdminMenuEntity;
import com.jk.model.AdminUsersEntity;

import java.util.List;

public interface AdminMenuService extends BaseService<AdminMenuEntity>{
    //后台菜单显示查询,关联菜单权限
    List<AdminMenuEntity> getMenuList(AdminUsersEntity adminUsersEntity);
    //查询菜单列表，更新伙添加菜单时用
    List<AdminMenuEntity> getMenu4UpdateList();
    //查询所有菜单
    List<AdminMenuEntity> getAllMenuList(AdminUsersEntity adminUsersEntity);
}
