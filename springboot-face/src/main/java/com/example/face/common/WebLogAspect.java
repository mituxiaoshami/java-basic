package com.example.face.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author: sea
 * @Description: 以controller为切点进行日志打印
 * @Date: 20:53 2017/10/30
 */
// AOP切面的优先级 在切入点前的操作，按order的值由小到大执行  在切入点后的操作，按order的值由大到小执行
// 在实际中order值可以设置为负值，确保是第一个进行执行的。
@Order(-1)
@Aspect
@Component
public class WebLogAspect {

    private static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    //定义开始时间 这里使用ThreadLocal的原因:
    // ThreadLocal会为每一个线程提供一个独立的变量副本，从而隔离了多个线程对数据的访问冲突。
    // 因为每一个线程都拥有自己的变量副本，从而也就没有必要对该变量进行同步了。
    // ThreadLocal提供了线程安全的共享对象，在编写多线程代码时，可以把不安全的变量封装进ThreadLocal。
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 定义一个切入点
     *
     * 第一个* :代表任意修饰符及任意返回值 这里直接用public代替
     * 第二个* :任意包名
     * 第二个* :任意类
     * 第三个* :任意方法
     * .. :匹配任意数量的参数
     */
    @Pointcut("execution(public * com.example.face.controller.*Controller.*(..))")
    public void webLog(){

    }


    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){

        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        logger.info("WebLogAspect.doBefore()");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();


        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        //获取所有参数方法一：
        Enumeration<String> enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            logger.info(paraName+": "+request.getParameter(paraName));
        }
    }


    @AfterReturning("webLog()")
    public void  doAfterReturning(JoinPoint joinPoint){
        // 处理完请求，返回内容
        logger.info("WebLogAspect.doAfterReturning()");
        logger.info("耗时(毫秒) : "+(System.currentTimeMillis()-startTime.get()));
    }



}
