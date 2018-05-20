package com.jk.service;

import com.jk.model.AdminRoleEntity;

import java.util.List;
import java.util.Map;

public interface AdminRoleService extends BaseService<AdminRoleEntity>{
    boolean deleteRoleMenu(String id);
    boolean insertRoleMenu(List<Map> mapList);
    boolean deleteRolePerm(String id);
    boolean insertRolePerm(String roleId,String perms);
}
