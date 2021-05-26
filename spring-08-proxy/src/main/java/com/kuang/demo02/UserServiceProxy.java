package com.kuang.demo02;

public class UserServiceProxy implements UserService{
    private UserServiceImpl userService;

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public void add() {
        log("add");
        userService.add();
    }

    @Override
    public void delete() {
        log("delete");
        userService.add();
    }

    @Override
    public void update() {
        log("update");
        userService.add();
    }

    @Override
    public void query() {
        log("query");
        userService.add();
    }
    //使用代理类，在不改变原有类的代码情况下，增加了log方法
    public void log(String msg) {
        System.out.println("使用了"+msg+"方法");
    }
}
