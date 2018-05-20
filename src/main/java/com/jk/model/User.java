package com.jk.model;

/**
 * Created by hukai on 2016/7/24.
 */
public class User extends BaseEntity{
    private int usersId;// 管理员ID
    private String userName;// 管理员名字
    private String userAccount;// 管理员登录账号
    private String password;// 管理员登录密码
    private int rolesId;// 管理员权限ID
    private String userDesc;// 管理员描述

    /*** getter setter ****/
    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRolesId() {
        return rolesId;
    }

    public void setRolesId(int rolesId) {
        this.rolesId = rolesId;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }
}
