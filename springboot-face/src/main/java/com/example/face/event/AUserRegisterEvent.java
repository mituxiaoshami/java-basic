package com.example.face.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Author: sea
 * @Description: 定义用户注册事件(注解)
 * @Date: 11:01 2018/4/24
 */
public class AUserRegisterEvent extends ApplicationEvent{

    public AUserRegisterEvent(String name) {
        super(name);
    }

}
