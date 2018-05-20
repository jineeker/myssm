package com.jk.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 请假信息
 * @Author hukai
 * @Email 614811431@qq.com
 * @Date 2017/7/30 15:50
 */
public class Leave implements Serializable{

    private String leaveId;//主键
    private String userId;//申请人id
    private String reason; 	// 请假原因
    private String processInstanceId; // 流程实例编号
    private Integer leaveDays;//请假天数
    private Date createDate;//创建时间

    /**vo**/
    private String taskName;//任务节点名
    private String taskUser;//当前任务人
    private String taskDesc;//任务审批备注
    private String taskId;//任务Id

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskUser() {
        return taskUser;
    }

    public void setTaskUser(String taskUser) {
        this.taskUser = taskUser;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }


    public String getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(String leaveId) {
        this.leaveId = leaveId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public Integer getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(Integer leaveDays) {
        this.leaveDays = leaveDays;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
