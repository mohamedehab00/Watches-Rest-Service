package com.rest.watchrestservice.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointCutExpressions {
    @Pointcut("execution(* com.rest.watchrestservice.serviceImpl.*.*(..))")
    public void serviceMethodsExecution(){
    }

    @Pointcut("execution(* com.rest.watchrestservice.serviceImpl.WatchServiceImpl.getAllWatchCategories())")
    public void getAllCategoriesMethodExecution(){
    }

    @Pointcut("execution(* com.rest.watchrestservice.serviceImpl.CustomerServiceImpl.*(..))")
    public void serviceCustomerMethodsExecution(){
    }
}
