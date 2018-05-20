package com.jk.dao;

import com.jk.model.BaseEntity;

import java.util.List;

/**
 * 基础dao层接口，在新的mapper中，写这些方法就直接可以调用了
 * @Author hukai
 * @Email 614811431@qq.com
 * @Date 2018-01-09 19:17
 */
public interface BaseDao<T extends BaseEntity> {
    int insert(T t);
    int delete(T t);
    int updateEntity(T t);
    List<T> queryList4Page(T t);
    T queryEntity(T t);
}
