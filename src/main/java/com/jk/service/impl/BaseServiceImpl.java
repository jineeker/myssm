package com.jk.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jk.dao.BaseDao;
import com.jk.model.BaseEntity;
import com.jk.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    @Autowired
    protected BaseDao<T> baseDao;

    @Override
    public boolean saveEntity(T t) {
        return baseDao.insert(t)==1?true:false;
    }

    @Override
    public boolean removeEntity(T t) {
        return baseDao.delete(t)==1?true:false;
    }

    @Override
    public boolean updateEntity(T t) {
        return baseDao.updateEntity(t)==1?true:false;
    }

    @Override
    public Page getList4Page(T t, Integer page, Integer rows) {
        Page resultPage = PageHelper.startPage(page, rows);
        baseDao.queryList4Page(t);
        return resultPage;
    }

    @Override
    public T getEntity(T t) {
        return baseDao.queryEntity(t);
    }
}
