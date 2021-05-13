package com.kuang.pojo;

public class User {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println("name: " + name + " age: " + age);
    }
}
