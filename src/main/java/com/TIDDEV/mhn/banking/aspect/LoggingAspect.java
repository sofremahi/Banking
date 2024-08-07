package com.TIDDEV.mhn.banking.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* com.TIDDEV.mhn.banking.web.*.*(..))")
    public void controllerMethods() {
    }

//    @Before("controllerMethods()")
//    public void logBefore(JoinPoint joinPoint) {
//        log.info("Executing method: " + joinPoint.getSignature().getName());
//        log.info("With arguments: " + Arrays.toString(joinPoint.getArgs()));
//    }
//
//    @AfterReturning(pointcut = "controllerMethods()", returning = "result")
//    public void logAfterReturning(JoinPoint joinPoint, Object result) {
//        log.info("Method executed: " + joinPoint.getSignature().getName());
//        log.info("Return value: " + result);
//    }
//
//    @AfterThrowing(pointcut = "controllerMethods()", throwing = "error")
//    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
//        log.error("Method executed: " + joinPoint.getSignature().getName());
//        log.error("Exception thrown: " + error);
//    }
//
//    @After("controllerMethods()")
//    public void logAfter(JoinPoint joinPoint) {
//        log.info("Finished executing method: " + joinPoint.getSignature().getName());
//    }

    @Around("controllerMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Method execution started: " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        log.info("Method execution completed: " + joinPoint.getSignature().getName());

        return result;
    }
}
