package com.jk.web;

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

	@RequestMapping("index.do")
	public String index(){
		log.info("welcome to admin page");
		return "/admin/index";
	}
	@RequestMapping("userView.do")
	public String userView(){
		return "/admin/userList";
	}

	@RequestMapping("user.do")
	public @ResponseBody Map<String,Object> user(HttpServletRequest request, HttpServletResponse response){

		Map<String,Object> map = new HashMap<String, Object>();
		List<User> list = userService.queryAllUser(1,10);
		map.put("rows", list);
		map.put("total",list.size());

		return map;
	}

}
