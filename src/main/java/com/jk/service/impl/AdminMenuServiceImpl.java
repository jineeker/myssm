package com.jk.service.impl;

import com.jk.dao.AdminMenuDao;
import com.jk.model.AdminMenuEntity;
import com.jk.model.AdminUsersEntity;
import com.jk.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminMenuServiceImpl extends BaseServiceImpl<AdminMenuEntity> implements AdminMenuService {

    @Autowired
    protected AdminMenuDao adminMenuDao;

    @Override
    public List<AdminMenuEntity> getMenuList(AdminUsersEntity adminUsersEntity) {
        return adminMenuDao.queryMenuList4Show(adminUsersEntity);
    }

    @Override
    public List<AdminMenuEntity> getMenu4UpdateList() {
        return adminMenuDao.queryMenu4UpdateList(new AdminMenuEntity());
    }

    @Override
    public List<AdminMenuEntity> getAllMenuList(AdminUsersEntity adminUsersEntity) {
        return adminMenuDao.queryAllList(adminUsersEntity);
    }
}
