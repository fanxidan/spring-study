package com.kuang.pojo;

import javax.annotation.Resource;

public class People2 {
    @Resource(name = "cat2")
    private Cat cat;
    @Resource(name = "dog")
    private Dog dog;
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

    public String getName() {
        return name;
    }

}
