package com.example.face.service.impl;

import com.example.face.event.AUserRegisterEvent;
import com.example.face.event.UserRegisterEvent;
import com.example.face.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @Author: sea
 * @Description: 用户业务逻辑
 * @Date: 10:34 2018/4/24
 */
@Service
public class UserServiceImpl implements UserService, ApplicationContextAware{

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 原始方式 实现事件发布
     */
    private ApplicationContext applicationContext;

    public void register(String name) {
        logger.info("用户: " + name + " 已注册! ");
        applicationContext.publishEvent(new UserRegisterEvent(name));
    }

    // 注入applicationContext
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
    }


    /**
     * 注解式的事件发布
     *
     * Spring4.2之后，ApplicationEventPublisher自动被注入到容器中，采用Autowired即可获取。
     */
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void annotationRegister(String name) {
        logger.info("用户: " + name + " 已注册! ");
        applicationEventPublisher.publishEvent(new AUserRegisterEvent(name));
    }

}
