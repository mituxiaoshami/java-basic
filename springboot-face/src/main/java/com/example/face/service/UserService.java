package com.example.face.service;

/**
 * @Author: sea
 * @Description: 用户服务接口
 * @Date: 10:33 2018/4/24
 */
public interface UserService {

    /**
     * 用户注册
     * @param name
     */
    void register(String name);

    void annotationRegister(String name);
}
