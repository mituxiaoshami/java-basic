package com.example.face.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Author: sea
 * @Description: 定义用户注册事件(原始)
 * @Date: 10:31 2018/4/24
 */
public class UserRegisterEvent extends ApplicationEvent{

    // ApplicationEvent是由Spring提供的所有Event类的基类，为了简单起见，注册事件只传递了name（可以复杂的对象，但注意要了解清楚序列化机制）。
    public UserRegisterEvent(String name) {  // name即source
        super(name);
    }


}
