/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jk.web;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.jk.base.BaseController;
import com.jk.model.Leave;
import com.jk.service.ILeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 请假Controller
 * @author ThinkGem
 * @version 2013-11-03
 */
@Controller
@RequestMapping(value = "${adminPath}/leave")
public class LeaveController extends BaseController {

	@Autowired
	private ILeaveService leaveService;

	/**
	 * 请假信息页面
	 */
	@RequestMapping(value = {"listView", ""})
	public String leaveListView(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/admin/leaveList";
	}

	/**
	 * 请假信息列表
	 */
	@RequestMapping(value = {"list"})
	public  @ResponseBody Map<String,Object> leaveList(String category,
			HttpServletRequest request, HttpServletResponse response, Model model) {

		String pageNum = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		if(StringUtil.isEmpty(pageNum)){
			pageNum = "1";
			pageSize = "10";
		}

		Map<String,Object> map = new HashMap<String, Object>();
		List<Leave> list = leaveService.getAllLeave4Page(null,Integer.parseInt(pageNum),Integer.parseInt(pageSize));

		//用PageInfo对结果进行包装，来获取分页需要的数据
		PageInfo pageInfo = new PageInfo(list);
		map.put("rows", list);
		map.put("total",pageInfo.getTotal());

		return map;
	}

	/**
	 * 添加请假申请
	 */
	@RequestMapping(value = {"addLeave"})
	public  @ResponseBody Map<String,Object> addLeave(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String, Object>();

		Leave leave = new Leave();
		leave.setUserId("666");
		leave.setLeaveId(UUID.randomUUID().toString());
		leave.setReason("世界那么大我要去看看");
		leave.setStartTime(new Date());
		leave.setEndTime(new Date());
		leave.setLeaveDays(3);
		leave.setCreateDate(new Date());

		leaveService.addLeave(leave);

		map.put("success","success");
		map.put("message","提交成功");

		return map;
	}
}
