package com.example.face.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: sea
 * @Description: 开启aop自动配置
 * @Date: 13:54 2017/11/6
 */
@Configuration
//开启AOP代理自动配置 如果是@SpringBootApplication注解 则不需要 因为已经开启自动配置
@EnableAspectJAutoProxy
public class AopConfig {
    
}
