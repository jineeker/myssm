package com.jk.model;

/**
 * 系统后台用户
 * @Author hukai
 * @Email 614811431@qq.com
 * @Date 2017-11-04 18:29
 */
public class AdminUsersEntity  extends BaseEntity{
    //sys_users
    private String id;//主键
    private String username;//用户名
    private String password;//密码
    private String passwordSalt;//密码加密盐
    private String locked;//是否锁定0否1是

    /**vo**/
    private String roleName;//角色名
    private String roleId;//角色id

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }
}
