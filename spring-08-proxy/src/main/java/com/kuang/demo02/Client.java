package com.kuang.demo02;

public class Client {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.add();
        // 使用代理的方式
        System.out.println("使用代理的方式");
        UserServiceProxy proxy = new UserServiceProxy();
        proxy.setUserService(userService);
        proxy.add();//不改变原有类，增加了方法
    }
}
