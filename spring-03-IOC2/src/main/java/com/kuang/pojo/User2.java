package com.kuang.pojo;

public class User2 {
    private String name;
    private int age;

    public User2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void show(){
        System.out.println("name: " + name + " age: " + age);
    }
}
