package com.example.ddd.config.performance_monitor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
public class PerformanceMonitorAspect {

    @Around("@annotation(PerformanceMonitor)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        long start = System.currentTimeMillis();
//
//        Object proceed = joinPoint.proceed();
//
//        long executionTime = System.currentTimeMillis() - start;
//
//        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
//        return proceed;

//        StopWatch stopwatch = new StopWatch();
//        stopwatch.start();
        StopWatch stopwatch = StopWatch.createStarted();
        Object proceed = joinPoint.proceed();
        stopwatch.stop();
        long timeElapsed_in_ms = stopwatch.getTime(TimeUnit.MILLISECONDS);
        log.info("{} completed using {} ms", joinPoint.getSignature().toShortString() , timeElapsed_in_ms);
        return proceed;
    }


}
