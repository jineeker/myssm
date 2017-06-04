package com.jk.web;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.jk.model.User;
import com.jk.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;

	Logger log = Logger.getLogger(AdminController.class);

	/**
	 * 进入后台首页
	 * @return
     */
	@RequestMapping("index.do")
	public String index(){
		log.info("welcome to admin page");
		return "/admin/index";
	}

	/**
	 * 页面跳转至查询用户信息
	 * @return
     */
	@RequestMapping("userView.do")
	public String userView(){
		return "/admin/userList";
	}

	/**
	 * 查询用户信息
	 * @param request
	 * @param response
     * @return
     */
	@RequestMapping("user.do")
	public @ResponseBody Map<String,Object> user(HttpServletRequest request, HttpServletResponse response){

		String page = request.getParameter("page");
		String rows = request.getParameter("rows");

		if(StringUtil.isEmpty(page)){
			page = "1";
			rows = "10";
		}
		Map<String,Object> map = new HashMap<String, Object>();
		List<User> list = userService.getAllUser4Page(null,Integer.parseInt(page),Integer.parseInt(rows));
		//用PageInfo对结果进行包装，来获取分页需要的数据
		PageInfo pageInfo = new PageInfo(list);
		map.put("rows", list);
		map.put("total",pageInfo.getTotal());

		return map;
	}

	/**
	 * 页面跳转至修改用户
	 * @return
	 */
	@RequestMapping("userUpdateView.do")
	public String userUpdateView(HttpServletRequest request, HttpServletResponse response){
		String userAccount = request.getParameter("userAccount");
		if(StringUtil.isNotEmpty(userAccount)){
			User user = new User();
			user.setUserAccount(userAccount);
			user = userService.queryUser(user);

			request.setAttribute("user",user);
		}

		return "/admin/userUpdate";
	}

	/**
	 * 页面跳转至修改用户
	 * @return
	 */
	@RequestMapping("userUpdate.do")
	public @ResponseBody Map<String,Object> userUpdate(HttpServletRequest request, HttpServletResponse response){
		String usersId = request.getParameter("usersId");
		String userName = request.getParameter("userName");
		String userDesc = request.getParameter("userDesc");

		Map<String,Object> map4result = new HashMap<String, Object>();
		User user = new User();
		user.setUserName(userName);
		user.setUserDesc(userDesc);
		if(StringUtil.isNotEmpty(usersId)){
			//更新
			user.setUsersId(Integer.parseInt(usersId));//有id
			if(userService.updateUser(user)){
				System.out.println(userService.updateUser(user));
				map4result.put("success","true");
				return map4result;
			}
			map4result.put("success","false");
		}else{
			//添加
			if(userService.saveUser(user)){
				map4result.put("success","true");
				return map4result;
			}
			map4result.put("success","false");
		}
		return map4result;
	}
}
