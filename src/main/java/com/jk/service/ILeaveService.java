package com.jk.service;

import com.jk.model.Leave;

import java.util.List;

/**
 * Created by hukai on 2016/9/9.
 */
public interface ILeaveService {
    boolean addLeave(Leave leave);
    Leave queryLeave(Leave leave);
    List<Leave> getAllLeave4Page(Leave leave, Integer page, Integer rows);
}
