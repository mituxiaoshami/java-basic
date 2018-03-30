package com.spring.demo.service.impl;

import com.spring.mvc.annotation.Service;
import com.spring.demo.service.DemoService;

/**
 * @Author: sea
 * @Description: Demo业务层
 * @Date: 19:54 2018/3/27
 */
@Service
public class DemoServiceImpl implements DemoService{

    public String get(String name) {

        String result = "Hello World !!! " + name;

        return result;
    }

}
