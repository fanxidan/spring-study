package com.kuang.demo03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//用于自动生成代理类
public class ProxyInvocationHandler implements InvocationHandler {
    //被代理的接口
    private RentInterface rent;

    public void setRent(RentInterface rent) {
        this.rent = rent;
    }

    //生成得到代理类
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), rent.getClass().getInterfaces(), this);
    }
    //处理代理实例并返回结果
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //2 调用ProxyInvocationHandler的seeHouse方法
        seeHouse();
        //动态代理的本质是使用反射机制实现
        //3 调用ProxyInvocationHandler的rent方法
        Object invoke = method.invoke(rent, args);
        //4 调用ProxyInvocationHandler的fare方法
        fare();
        return invoke;
    }
    public void seeHouse(){
        System.out.println("中介带看房子");
    }
    public void fare(){
        System.out.println("收中介费");
    }
}
