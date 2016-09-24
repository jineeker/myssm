//package com.jk.aspect;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Created by hukai on 2016/9/12.
// */
//public class TimeAspect2 {
//    private static final Log logger = LogFactory.getLog(TimeAspect2.class);
//
//    public void befor(){
//        logger.info("=====================befor============");
//    }
//
//    public Object around(ProceedingJoinPoint joinPoint){
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//
//        Object object = null;
//        try {
//            long startTime = System.currentTimeMillis();
//            object = joinPoint.proceed();
//            //获取方法名
//            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//            String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
//
//            long endTime = System.currentTimeMillis();
//            logger.info("用户"+request.getParameter("name")+"正在操作:"+methodName+",本次花费："+(endTime-startTime)+"ms");
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        return object;
//    }
//
//    public void after(){
//        logger.info("=======================after=============");
//    }
//}
