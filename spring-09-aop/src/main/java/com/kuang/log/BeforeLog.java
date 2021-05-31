package com.kuang.log;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class BeforeLog implements MethodBeforeAdvice {
    //method:要执行的目标对象的方法
    //objects:参数
    //o:目标对象
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("前置通知: " + o.getClass().getName()+"的"+method.getName()+"被执行了");
    }
}
