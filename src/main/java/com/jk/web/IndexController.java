package com.jk.web;

import com.jk.model.User;
import com.jk.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 网站首页
 * @Author hukai
 * @Email 614811431@qq.com
 * @Date 2016/9/9 16:26
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserService userService;

    private static final Log log = LogFactory.getLog(IndexController.class);

    @RequestMapping("index.xhtml")
    public String indexView(
            HttpServletRequest request, HttpServletResponse response){
        ServletContext application = request.getSession().getServletContext();

        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);


        return "index";
    }

    /**
     * 用户登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("login.xhtml")
    public String login(
            HttpServletRequest request, HttpServletResponse response){

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
            return "index2";
        }else{
            request.getSession().removeAttribute("userId");
            request.getSession().removeAttribute("name");
        }
        request.setAttribute("success","false");
        return "/front/index";
    }

//    /**
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("query.xhtml")
//    public @ResponseBody Map<String,Object> query(
//            HttpServletRequest request, HttpServletResponse response){
//        userService.queryAllUser();
//        Map<String,Object> map = new HashMap<String, Object>();
//        map.put("success","success");
//        return map;
//    }
}
