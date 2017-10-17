package com.jk.service.impl;

import com.github.pagehelper.PageHelper;
import com.jk.dao.LeaveDao;
import com.jk.model.Leave;
import com.jk.service.ILeaveService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hukai on 2016/9/9.
 */
@Service
public class LeaveServiceImpl implements ILeaveService {

    @Autowired
    protected LeaveDao leaveDao;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Override
    public boolean addLeave(Leave leave) {
        if(null!=leave.getLeaveId()){
            if(leaveDao.addLeave(leave)>0){
                // 启动流程
                String businessKey = leave.getLeaveId().toString();

                Map<String, Object> variables = new HashMap<String, Object>();
                leave.setBusinessType("leave");
                leave.setBusinessKey(businessKey);
                leave.setUser_name("XXX");
                leave.setTitle("XXX的请假");

                variables.put("entity",leave);

                ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("LeaveBill", businessKey, variables);

                leave.setProcessInstanceId(processInstance.getProcessInstanceId());

                leaveDao.updateLeave(leave);

                System.out.println("启动流程成功，流程id="+processInstance.getProcessInstanceId());
            }
        }

        return true;
    }

    @Override
    public Leave queryLeave(Leave leave) {
        return leaveDao.getLeave(leave);
    }

    @Override
    public List<Leave> getAllLeave4Page(Leave leave,Integer page,Integer rows) {
        PageHelper.startPage(page, rows);
        List<Leave> list = leaveDao.getAllLeave(leave);
        for(Leave entity:list){
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(entity.getProcessInstanceId()).list();
            if(tasks.size()>0){
                Task task = tasks.get(0);
                entity.setTaskUser(task.getAssignee());
                entity.setTaskName(task.getName());

                String comment = (String) taskService.getVariable(task.getId(), "comment");
                entity.setTaskDesc(comment);
            }
            System.out.println(tasks.size());
        }
        return list;
    }

}
