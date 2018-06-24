package com.example.demo.aspect;

import com.example.demo.web.DemoController;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {

    final Logger log = LoggerFactory.getLogger(HttpAspect.class);
//
//    @Before("execution(public * com.example.demo.web.UserController.*(..))")
//    public void log(){
//        log.info("before");
//    }
//
//    @After("execution(public * com.example.demo.web.UserController.*(..))")
//    public void doAfter(){
//        log.info("after");
//    }


    @Pointcut("execution(public * com.example.demo.web.UserController.*(..))")
    public void log() {
    }

    @Before("log()")
    public void before(JoinPoint point) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        log.info("url={}",request.getRequestURI());
        //method
        log.info("method={}",request.getMethod());
        //ip
        log.info("ip={}",getAddr(request));
        //类方法
        log.info("class_method={}",point.getSignature().getDeclaringTypeName()+"."+point.getSignature().getName());
        //参数
        log.info("args={}",point.getArgs());
    }


    @After("log()")
    public void after() {
        log.info("after");
    }


    private static String getAddr(HttpServletRequest request){
        String ip=request.getHeader("x-forwarded-for");
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("Proxy-Client-IP");
        }
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getRemoteAddr();
        }
        return ip;
    }
}
