package com.jk.model;

import java.util.List;

/**
 * 后台系统菜单实体
 * @Author hukai
 * @Email 614811431@qq.com
 * @Date 2017-10-18 19:46
 */
public class AdminMenuEntity extends BaseEntity{
    //admin_menu
    private String menuId;//菜单主键
    private String menuName;//菜单名称
    private String menuIcon;//菜单图标
    private String menuUrl;//访问路径
    private String menuPid;//父菜单id
    private Integer menuSort;//排序标识

    /**vo**/
    private String menuPname;//父菜单名称
    private List<AdminMenuEntity> children;

    public List<AdminMenuEntity> getChildren() {
        return children;
    }

    public void setChildren(List<AdminMenuEntity> children) {
        this.children = children;
    }

    public String getMenuPname() {
        return menuPname;
    }

    public void setMenuPname(String menuPname) {
        this.menuPname = menuPname;
    }

    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuPid() {
        return menuPid;
    }

    public void setMenuPid(String menuPid) {
        this.menuPid = menuPid;
    }
}
