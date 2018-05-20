package com.jk.service.impl;

import com.jk.dao.AdminRoleDao;
import com.jk.model.AdminRoleEntity;
import com.jk.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminRoleServiceImpl extends BaseServiceImpl<AdminRoleEntity> implements AdminRoleService {

    @Autowired
    protected AdminRoleDao adminRoleDao;

    @Override
    public boolean deleteRoleMenu(String id) {
        return adminRoleDao.deleteRoleMenu(id)>0?true:false;
    }

    @Override
    public boolean insertRoleMenu(List<Map> mapList) {
        return adminRoleDao.insertRoleMenu(mapList)>0?true:false;
    }

    @Override
    public boolean deleteRolePerm(String id) {
        return adminRoleDao.deleteRolePerm(id)>=0?true:false;
    }

    @Override
    public boolean insertRolePerm(String roleId,String perms) {
        return adminRoleDao.insertRolePerm(roleId,perms)==1?true:false;
    }
}
