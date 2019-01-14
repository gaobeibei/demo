package com.example.demo.aspect;

import com.example.demo.entity.SysLog;
import com.example.demo.repository.SysLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Aspect
@Component
public class LogAop {

    final Logger log = LoggerFactory.getLogger(LogAop.class);

    @Autowired
    SysLogRepository sysLogRepository;

    /**
     * 方式1
     */
//    @Before("execution(public * com.example.demo.web.UserController.*(..))")
//    public void doBefore() {
//        log.info("before");
//    }
//
//    @After("execution(public * com.example.demo.web.UserController.*(..))")
//    public void doAfter() {
//        log.info("after");
//    }

    /**
     * 方式2
     */
//    @Pointcut("execution(public * com.example.demo.web.UserController.*(..))")
//    public void log(){
//    }
//
//    @Before("log()")
//    public void doBefore() {
//        log.info("before");
//    }
//
//    @After("log()")
//    public void doAfter() {
//        log.info("after");
//    }
    @Pointcut("execution(public * com.example.demo.web.UserController.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //URL
        log.info("url={}", request.getRequestURI());
        //Method
        log.info("method={}", request.getMethod());
        //Ip
        log.info("ip={}", request.getRemoteAddr());
        //类方法
        log.info("类方法={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //Param
        log.info("param={}", joinPoint.getArgs());

        SysLog log = new SysLog();
        log.setArgs(joinPoint.getArgs().toString());
        log.setIp(request.getRemoteAddr());
        log.setMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.setUrl(request.getRequestURI());
        log.setTime(new Date());
        sysLogRepository.save(log);
    }

    @After("log()")
    public void doAfter() {
        log.info("after");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        log.info("response={}",object.toString());
    }


}
