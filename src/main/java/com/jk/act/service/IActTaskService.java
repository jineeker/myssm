/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jk.act.service;

import com.github.pagehelper.Page;
import com.jk.act.entity.BaseVO;
import com.jk.model.User;

import java.util.List;

/**
 * 流程定义相关Service
 * @author ThinkGem
 * @version 2013-11-03
 */
public interface IActTaskService {

	/**
	 * 获取待办列表,包括已签收的和待签收的
	 * @param user
	 * @param page
     * @return
     */
	public List<BaseVO> todoList(User user,Page<BaseVO> page);

}
