package com.spring.demo.controller;

import com.spring.mvc.annotation.Autowrited;
import com.spring.mvc.annotation.Controller;
import com.spring.mvc.annotation.RequestMapping;
import com.spring.mvc.annotation.RequestParam;
import com.spring.demo.service.DemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Demo控制器
 */
@Controller
public class DemoController {

    @Autowrited
    private DemoService demoService;

    @RequestMapping("/query")
    public void query(HttpServletRequest request, HttpServletResponse response, @RequestParam("name") String name) {
        String result = this.demoService.get(name);
        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/edit")
    public void edit(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Integer id) {


    }

    @RequestMapping("/remove")
    public void remove(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Integer id) {


    }


}
