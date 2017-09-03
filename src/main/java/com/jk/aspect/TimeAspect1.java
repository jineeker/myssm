//package com.jk.aspect;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Aspect
//@Component
//public class TimeAspect1 {
//
//    private static Log logger = LogFactory.getLog(TimeAspect1.class);
//
//    // service层的统计耗时切面，类型必须为final String类型的,注解里要使用的变量只能是静态常量类型的
//    public static final String POINT = "execution(* com.jk.web.*.*(..))";
//
//    /**
//     * 在切入点之前执行
//     */
//    @Pointcut
//    @Before(POINT)
//    public void doBefore() {
////        logger.info("==========================befor================================");
//    }
//
//    /**
//     * 统计方法执行耗时Around环绕通知
//     * @param joinPoint
//     * @return
//     */
//    @Around(POINT)
//    public Object timeAround(ProceedingJoinPoint joinPoint) {
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
//            logger.info("您好,用户【"+request.getSession().getAttribute("name")+"】正在操作:"+signature.getName()+",本次花费："+(endTime-startTime)+"ms");
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        return object;
//    }
//
//    /**
//     * 在切入点之后执行
//     */
//    @After(POINT)
//    public void doAfter() {
////        logger.info("==========================after================================");
//    }
//}