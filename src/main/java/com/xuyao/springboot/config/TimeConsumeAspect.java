package com.xuyao.springboot.config;

import com.xuyao.springboot.annotation.TimeConsume;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeConsumeAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeConsumeAspect.class);

    @Around("@annotation(timeConsume)")
    public Object around(ProceedingJoinPoint joinPoint, TimeConsume timeConsume) throws Throwable {
        Object proceed;
        if(timeConsume != null){
            long start = System.currentTimeMillis();
            proceed = joinPoint.proceed();
            long end = System.currentTimeMillis();
            LOGGER.info("类{}，方法{}，耗时{}", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), (end - start) + "ms");
        }else{
            proceed = joinPoint.proceed();
        }
        return proceed;
    }

}