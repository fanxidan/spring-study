package com.kuang.pojo;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

public class People5 {
    @Resource(name = "cat2")
    private Cat cat;
    @Resource(name = "dog2")
    private Dog dog;
    @Value("fxd")
    private String name;

    @Override
    public String toString() {
        return "People{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }

    public Cat getCat() {
        return cat;
    }

    public Dog getDog() {
        return dog;
    }
}
