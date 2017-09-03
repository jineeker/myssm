package com.jk.dao;

import com.jk.model.Leave;

import java.util.List;

/**
 * Created by hukai on 2016/9/9.
 */
public interface LeaveDao {
    int addLeave(Leave leave);
    Leave getLeave(Leave leave);
    List<Leave> getAllLeave(Leave leave);
    int updateLeave(Leave leave);
}
