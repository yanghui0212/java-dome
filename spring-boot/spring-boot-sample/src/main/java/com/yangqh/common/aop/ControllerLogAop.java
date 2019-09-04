package com.yangqh.common.aop;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.yangqh.nosql.entity.ControllerLogMongo;
import com.yangqh.nosql.repository.ControllerLogMongoRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author yangq
 * Create time in 2018/07/10 14:08
 */
@Aspect
@Component
@Order(5)
public class ControllerLogAop {
    private Logger logger = LoggerFactory.getLogger(ControllerLogAop.class);
    ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    @Autowired
    private ControllerLogMongoRepository logMongoRepository;

    @Pointcut("execution(public * com.yangqh.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        threadLocal.set(System.currentTimeMillis());
    }


    @After("webLog()")
    public void doAfter(JoinPoint joinPoint) {
        // 获取HttpServletRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        ControllerLogMongo logMongo = new ControllerLogMongo();
        logMongo.setRequestURL(request.getRequestURL().toString());
        logMongo.setRequestURI(request.getRequestURI());
        logMongo.setQueryString(request.getQueryString());
        logMongo.setRemoteAddr(request.getRemoteAddr());
        logMongo.setRemoteHost(request.getRemoteHost());
        logMongo.setRemotePort(request.getRemotePort());
        logMongo.setLocalAddr(request.getLocalAddr());
        logMongo.setLocalName(request.getLocalName());
        logMongo.setMethod(request.getMethod());
        logMongo.setHeaders(new Gson().toJson(getHeadersInfo(request)));
        logMongo.setParameters(new Gson().toJson(request.getParameterMap()));
        logMongo.setClassMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logMongo.setArgs(Arrays.toString(joinPoint.getArgs()));
        logMongo.setTimes(System.currentTimeMillis() - threadLocal.get());
        logMongoRepository.insert(logMongo);
    }


    /**
     * 获取头信息
     *
     * @param request
     * @return
     */
    private Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = Maps.newHashMap();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}
