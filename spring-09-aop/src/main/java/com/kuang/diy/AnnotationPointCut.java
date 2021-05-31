package com.kuang.diy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

//使用注解方式实现AOP
@Aspect
public class AnnotationPointCut {
    @Before("execution(* com.kuang.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("方法执行前");
    }

    @After("execution(* com.kuang.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("方法执行后");
    }

    //在环绕增强中，可以给定一个参数，代表要获取处理的切入点
    @Around("execution(* com.kuang.service.UserServiceImpl.*(..))")
    public void arround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕前");
        System.out.println(proceedingJoinPoint.getSignature());//获取签名:即切入的类名.方法名
        // 执行add方法前后执行前置通知与后置通知
        Object proceed = proceedingJoinPoint.proceed();//执行方法
        System.out.println("环绕后");
    }
}
