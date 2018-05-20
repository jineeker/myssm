package com.jk.web;

import com.jk.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "${adminPath}/index")
public class AdminController {

	@Autowired
	private UserService userService;

	private static final Logger LOGGER = Logger.getLogger(AdminController.class);

	//权限不足页面
	@RequestMapping(value = {"nopermiss.do"})
	public String nopermiss(){
		return "/error/nopermiss";
	}
	/**
	 * 进入登录页面
	 * @return
	 */
	@RequestMapping(value = {"loginView.do"})
	public String loginView(){
		return "/admin/login";
	}

	@RequestMapping(value = "login.do")
	public ModelAndView login(HttpServletRequest req, HttpServletResponse resp,
							  String username,String password){

		Map map = new HashMap();
		if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
			map.put("message","用户名密码不能为空");
			return new ModelAndView("/admin/login",map);
		}
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		//记录该令牌
		token.setRememberMe(false);
		//subject权限对象
		Subject subject = SecurityUtils.getSubject();

		try {
			subject.login(token);
		} catch (UnknownAccountException ex) {//用户名没有找到
			map.put("message","用户名没有找到");
			return new ModelAndView("/admin/login",map);
		} catch (IncorrectCredentialsException ex) {
			map.put("message","用户名密码不匹配");
			return new ModelAndView("/admin/login",map);
		} catch (LockedAccountException ex) {
			map.put("message","用户被锁定");
			return new ModelAndView("/admin/login",map);
		} catch (AuthenticationException e) {
			map.put("message","其他的登录错误");
			return new ModelAndView("/admin/login",map);
		}

		//验证是否成功登录的方法
		if (subject.isAuthenticated()) {
			return new ModelAndView("redirect:/k/index/index.do");
		}
		return new ModelAndView("/admin/login",map);
	}

	/**
	 * 进入后台首页
	 * @return
     */
	@RequestMapping(value = {"index.do", ""})
	public String index(){
		LOGGER.info("welcome to admin page");
		return "/admin/index";
	}

}
