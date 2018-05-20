package com.jk.dao;

import com.jk.model.AdminRoleEntity;

import java.util.List;
import java.util.Map;

public interface AdminRoleDao extends BaseDao<AdminRoleEntity>{
    int deleteRoleMenu(String id);
    int insertRoleMenu(List<Map> mapList);
    int deleteRolePerm(String id);
    int insertRolePerm(String roleId,String perms);
}
