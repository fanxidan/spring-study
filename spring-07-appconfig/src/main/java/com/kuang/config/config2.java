package com.kuang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(config1.class)
public class config2 {
//直接使用config1的配置
}