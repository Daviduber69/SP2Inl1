package com.yrgo.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component
public class PerformanceTimingAdvice {
    public Object performTimingMeasurement (ProceedingJoinPoint method) throws Throwable {

            long startTime =System.nanoTime();

            try {
                Object value= method.proceed();
                return value;
            }finally {
                long endTime= System.nanoTime();
                long timeTaken = endTime - startTime;
                System.out.println("The method " +
                        method.getSignature().getName() +" in the " +method.getTarget().getClass() + " took " + timeTaken
                        /1000000 + " ms");
            }
        }
    }

