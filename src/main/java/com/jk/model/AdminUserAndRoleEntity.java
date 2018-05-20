package com.jk.model;

/**
 * 用户与角色关联
 * @Author hukai
 * @Email 614811431@qq.com
 * @Date 2018-01-21 16:22
 */
public class AdminUserAndRoleEntity extends BaseEntity{
    //sys_user_role
    private String id;//主键
    private String sysUserId;//用户id
    private String sysRoleId;//角色id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
    }
}
