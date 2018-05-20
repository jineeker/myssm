package com.jk.web;

/**
 * 全局异常处理类
 * @Author hukai
 * @Email 614811431@qq.com
 * @Date 2017-11-05 11:31
 */
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AllExceptionResolver implements HandlerExceptionResolver {
    private static final Logger logger = Logger.getLogger(AllExceptionResolver.class);

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        Map<String, Object> model = new ConcurrentHashMap<String, Object>();

        // 可以细化异常信息，给出相应的提示
        logger.info("==========发生了异常：");
        logger.info("==========异常类型："+ex.getClass().getSimpleName());
        logger.info("==========异常描述："+ex.getMessage());
        logger.info("==========异常原因："+ex.getCause());

        //针对不同的异常可以做不同的提示或处理
        if("UnauthorizedException".equals(ex.getClass().getSimpleName())){
            model.put("info", "对不起，您的权限不足！");
        }else{
            try {
                throw ex;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ModelAndView("error/error",model);
    }
}
