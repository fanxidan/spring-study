package com.kuang.demo03;

public class Client {
    public static void main(String[] args) {
        Landlord landlord = new Landlord();
        //代理角色
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        //通过调用程序处理角色来处理要调用的接口对象
        pih.setRent(landlord);
        RentInterface proxy = (RentInterface) pih.getProxy();
        //1 调用ProxyInvocationHandler的invoke方法
        proxy.rent();
    }
}
