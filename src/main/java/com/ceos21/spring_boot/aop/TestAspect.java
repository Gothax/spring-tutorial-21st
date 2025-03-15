package com.ceos21.spring_boot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Aspect
@Component
public class TestAspect {

    @Pointcut("execution(* com.ceos21.spring_boot.controller.*.*(..))")
    public void controllerAdvice() {

    }

    @Before("controllerAdvice()")
    public void requestLogging(JoinPoint joinPoint) {
        MDC.put("traceId", UUID.randomUUID().toString());

        log.info("REQUEST TRACING_ID : {}", MDC.get("traceId"));
    }

    @AfterReturning(pointcut = "controllerAdvice()", returning = "result")
    public void responseLogging(JoinPoint joinPoint, Object result) {
        log.info("RESPONSE TRACING_ID : {}", MDC.get("traceId"));
        MDC.clear();
    }
}
