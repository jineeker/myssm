package com.jk.web;

import com.jk.model.User;
import com.jk.redis.RedisCache;
import com.jk.service.UserService;
import org.apache.log4j.Logger;
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
public class IndexController {

    @Autowired
    private UserService userService;

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

}
