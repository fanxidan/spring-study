package com.kuang.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//<bean id="user" class="com.kuang.pojo.User"/>
@Component
//作用域
@Scope("singleton")
public class User {
    @Value("fanxidan")
    public String name;
}
