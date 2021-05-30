package com.kuang.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//类被Spring接管
@Component
public class User {
    private String name;

    public String getName() {
        return name;
    }

    @Value("fxd2")
    public void setName(String name) {
        this.name = name;
    }
}