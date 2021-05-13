package com.kuang.pojo;

public class User2 {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public User2() {
        System.out.println("User2 无参构造被调用");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println("name: " + name + " age: " + age);
    }
}
