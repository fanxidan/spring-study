package com.kuang.pojo;

public class User {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public User() {
        System.out.println("User2 无参构造被调用");
    }

    public void show(){
        System.out.println("name: " + name);
    }
}
