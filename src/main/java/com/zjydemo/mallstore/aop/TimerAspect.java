package com.zjydemo.mallstore.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author zjy
 * @version 1.0
 */

@Component
@Aspect
public class TimerAspect {

    // 环绕业务层中所有类的所有方法
    @Around("execution(* com.zjydemo.mallstore.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long l = System.currentTimeMillis();
        Object proceed = point.proceed(); // 调用目标方法
        long r = System.currentTimeMillis();
        System.out.println("耗时："+ (r-l));
        return proceed;
    }
}
