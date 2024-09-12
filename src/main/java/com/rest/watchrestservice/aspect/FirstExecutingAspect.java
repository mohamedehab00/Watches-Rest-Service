package com.rest.watchrestservice.aspect;

import com.rest.watchrestservice.dto.CategoryDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(1)
public class FirstExecutingAspect {
    @Before("com.rest.watchrestservice.aspect.PointCutExpressions.serviceMethodsExecution()")
    public void beforeAspectTest1(){
        System.out.println("Execution Of Service Method!! (First)");
    }

    @After("com.rest.watchrestservice.aspect.PointCutExpressions.serviceMethodsExecution()")
    public void afterAspectTest1(){
        System.out.println("Successful Execution Of Service Method!! (First)");
    }

    @AfterReturning(value = "com.rest.watchrestservice.aspect.PointCutExpressions.getAllCategoriesMethodExecution()", returning = "res")
    public void getAllCategoriesAspect(JoinPoint joinPoint, List<CategoryDto> res){
        res.forEach(categoryDto -> {
            categoryDto.setDescription(categoryDto.getDescription().toUpperCase());
        });
    }

    @Around("com.rest.watchrestservice.aspect.PointCutExpressions.getAllCategoriesMethodExecution()")
    public Object aroundAspect(ProceedingJoinPoint joinPoint) throws Throwable{

        long begin = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        System.out.println(STR."Time Taken = \{end - begin}");

        return result;
    }
}
 