package com.example.book.utils;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * todo
 * @author: wxh
 * @Date: 2020-07-11
 * @Description:
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {

    private String generateTraceId() {
        return java.util.UUID.randomUUID().toString();
    }

    /** 以 controller 包下定义的所有请求为切入点 */
    @Pointcut("execution(public * com.example.book.controller..*.*(..))")
    public void webLog() {}

    /**
     * 环绕
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String traceId = generateTraceId();
        log.info("{} URL: {}",traceId,url);
        log.info("{} Class Method   : {}.{}", traceId,proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName());
        log.info("{} IP             : {}", traceId,request.getRemoteAddr());
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        log.info("{} Response Args  : {}",traceId, new Gson().toJson(result));
        // 执行耗时
        log.info("{} Time-Consuming : {} ms",traceId, System.currentTimeMillis() - startTime);
        return result;
    }


}
