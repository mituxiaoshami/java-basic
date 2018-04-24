package com.example.face.service.impl;

import com.example.face.event.AUserRegisterEvent;
import com.example.face.event.UserRegisterEvent;
import com.example.face.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @Author: sea
 * @Description:
 * @Date: 10:39 2018/4/24
 */
@Service
public class EmailServiceImpl implements EmailService, ApplicationListener<UserRegisterEvent>{

    private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    /**
     * 使用原始方式监听事件
     * @param event 事件
     */
    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        logger.info("邮件服务接到通知，给 " + event.getSource() + " 发送邮件...");
    }


    /**
     * 使用注解式监听事件
     * @param event 事件
     */
    @EventListener
    public void listenerRegister(AUserRegisterEvent event) {
        logger.info("邮件服务接到通知，给 " + event.getSource() + " 发送邮件...");
    }


}
