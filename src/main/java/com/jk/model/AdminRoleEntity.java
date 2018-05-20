package com.jk.model;

/**
 * 后台用户角色
 * @Author hukai
 * @Email 614811431@qq.com
 * @Date 2017-11-04 18:29
 */
public class AdminRoleEntity extends BaseEntity{
    //sys_role
    private String id;//主键
    private String roleName;//名称
    private String available;//是否可用,1：可用，0不可用

    //vo
    private String rolePerms;//角色权限

    public String getRolePerms() {
        return rolePerms;
    }

    public void setRolePerms(String rolePerms) {
        this.rolePerms = rolePerms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}
