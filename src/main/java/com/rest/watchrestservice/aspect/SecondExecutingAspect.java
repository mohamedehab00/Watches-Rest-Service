package com.rest.watchrestservice.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class SecondExecutingAspect {
    @Before("com.rest.watchrestservice.aspect.PointCutExpressions.serviceMethodsExecution()")
    public void aopTest2(){
        System.out.println("Execution Of Service Method!! (Second)");
    }

    @After("com.rest.watchrestservice.aspect.PointCutExpressions.serviceMethodsExecution()")
    public void afterAspectTest1(){
        System.out.println("Successful Execution Of Service Method!! (Second)");
    }
}
