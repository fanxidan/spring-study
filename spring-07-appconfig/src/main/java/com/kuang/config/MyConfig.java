package com.kuang.config;

import com.kuang.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//被spring托管，注册到容器中，本省歧视是@Component
//这是配置类，等价于beans.xml
@Configuration
@ComponentScan("com.kuang.pojo")
public class MyConfig {
    //注册一个bean，相当于之前的bean标签，方法名字==bean标签中的id
    //返回值相当于bean标签中的class属性
    @Bean
    public User getUser(){
        return new User(); // 返回要注入到bean的对象
    }
}
