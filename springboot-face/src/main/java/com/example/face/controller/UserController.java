package com.example.face.controller;

import com.example.face.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: sea
 * @Description: 用户控制器
 * @Date: 10:41 2018/4/24
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("register")
    public void register() {
        this.userService.register("jack");
    }

    @GetMapping("register/annotation")
    public void annotationRegister() {
        this.userService.annotationRegister("james");
    }

}
