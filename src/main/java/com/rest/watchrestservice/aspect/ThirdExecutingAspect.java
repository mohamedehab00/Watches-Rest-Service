package com.rest.watchrestservice.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class ThirdExecutingAspect {
    @Before("com.rest.watchrestservice.aspect.PointCutExpressions.serviceMethodsExecution()")
    public void aopTest3(){
        System.out.println("Execution Of Service Method!! (Third)");
    }
}
