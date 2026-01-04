package com.springlove.api_demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    // AOP for execution service methods
    @Around("execution(* com.springlove.api_demo.service.*.*(..))")
    public Object aroundServiceMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // Get begin timestamp
        long begin = System.currentTimeMillis();

        // Executing the method
        Object result = proceedingJoinPoint.proceed();

        // Get end timestamp
        long end = System.currentTimeMillis();

        // Computing duration and display it
        long duration = end - begin;

        System.out.println("\n=====> " + proceedingJoinPoint.getSignature().toShortString() + ": " + duration + " ms.\n");
        return result;
    }

    // AOP for exception handling
    @AfterThrowing(pointcut = "execution(* com.springlove.api_demo.service.*.*(..))",
                    throwing = "exception")
    public void afterThrowingExceptionInServiceMethods(JoinPoint joinPoint, Throwable exception) {
        System.out.println("\n=====> " + joinPoint.getSignature().toShortString() + " exception is: \n" + exception + ".\n");
    }


}
