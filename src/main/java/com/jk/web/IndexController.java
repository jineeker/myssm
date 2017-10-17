package com.jk.web;

import com.jk.model.User;
import com.jk.redis.RedisCache;
import com.jk.service.IUserService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 网站首页
 * @Author hukai
 * @Email 614811431@qq.com
 * @Date 2016/9/9 16:26
 */
@Controller
@RequestMapping(value = "${path}/welcome")
public class IndexController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisCache redisCache;

    private static final Logger log = Logger.getLogger(IndexController.class);

    @RequestMapping("index.html")
    public String indexView(
            HttpServletRequest request, HttpServletResponse response){
        //清空所有缓存
        redisCache.clearCache();

        return "/front/index";
    }

    /**
     * 用户登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("login.html")
    public String login(HttpServletRequest request, HttpServletResponse response){

        String name = request.getParameter("name");
        String psd = request.getParameter("psd");

        User user = new User();
        user.setUserAccount(name);
        user.setPassword(psd);
        user = userService.queryUser(user);

        if(user!=null){
            request.getSession().setAttribute("userId",user.getUsersId());
            request.getSession().setAttribute("name",user.getUserName());
            request.setAttribute("success",user);
            //模拟将token存入缓存
            String token = UUID.randomUUID().toString();
            redisCache.putCacheWithExpireTime(token,user, RedisCache.CAHCETIME);
            log.info("====="+token+"==");

            //测试list序列化
            List<User> userList = new ArrayList();
            userList.add(user);
            User u = new User();
            u.setUserName("hk");
            userList.add(u);
            u = new User();
            u.setUserName("hukai");
            userList.add(u);
            redisCache.putCache("testList",userList);

            return "redirect:queryLoginUser.html";
        }else{
            request.getSession().removeAttribute("userId");
            request.getSession().removeAttribute("name");
        }
        request.setAttribute("success","false");
        return "/front/index";
    }

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("queryLoginUser.html")
    public @ResponseBody Map<String,Object> query(
            HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<String, Object>();
        List<User> userList = new ArrayList<User>();
        userList = (List<User>) redisCache.getCache("testList");
        map.put("tesst",userList);
        map.put("success","success");
        return map;
    }

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("query4token.html")
    public @ResponseBody Map<String,Object> query4token(
            HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<String, Object>();
        String token = request.getParameter("token");
        User user = (User) redisCache.getCache(token);
        log.info("userName"+user.getUserName());
        map.put("user",user);
        map.put("success","success");
        return map;
    }


    @RequestMapping("login4shiro.html")
    public String login4shiro(HttpServletRequest req, HttpServletResponse resp){

        String error = null;
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            error = "用户名/密码错误";
        } catch (IncorrectCredentialsException e) {
            error = "用户名/密码错误";
        } catch (AuthenticationException e) {
            //其他错误，比如锁定，如果想单独处理请单独catch处理
            error = "其他错误：" + e.getMessage();
        }
        if(error != null) {//出错了，返回登录页面
            req.setAttribute("error", error);
        } else {//登录成功
            log.info("登录成功");
            return "/front/index";
        }
        System.out.println(error);
        return "404";
    }

}
