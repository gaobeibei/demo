package com.example.demo.aspect;

import com.example.demo.entity.SysLog;
import com.example.demo.repository.SysLogRepository;
import com.example.demo.web.DemoController;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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
public class HttpAspect {

    final Logger log = LoggerFactory.getLogger(HttpAspect.class);

    @Autowired
    SysLogRepository sysLogRepository;

    @Pointcut("execution(public * com.example.demo.web.UserController.*(..))")
    public void log() {
    }

    @Before("log()")
    public void before(JoinPoint point) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        SysLog log = new SysLog();
        log.setIp(getAddr(request));
        log.setUrl(request.getRequestURI());
        log.setArgs(point.getArgs().toString());
        log.setTime(new Date());
        sysLogRepository.save(log);
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
