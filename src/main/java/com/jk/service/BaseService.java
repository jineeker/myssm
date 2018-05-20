package com.jk.service;

import com.github.pagehelper.Page;
import com.jk.model.BaseEntity;

/**
 * 基础服务接口,增删改差查
 * @Author hukai
 * @Email 614811431@qq.com
 * @Date 2018-01-08 18:13
 */
public interface BaseService<T extends BaseEntity> {

    boolean saveEntity(T t);
    boolean removeEntity(T t);
    boolean updateEntity(T t);
    Page getList4Page(T t, Integer page, Integer rows);
    T getEntity(T t);
}
