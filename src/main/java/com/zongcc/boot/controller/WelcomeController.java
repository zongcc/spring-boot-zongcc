package com.zongcc.boot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

/**
 * Created by chunchengzong on 2017-03-21.
 */
@Controller
@RequestMapping("/welcome")
public class WelcomeController {
    @Value("${application.message:Hello World}")
    private String message = "hello world";

    @RequestMapping("/")
    public ModelAndView welcome() {
        ModelAndView mvc = new ModelAndView();
        mvc.addObject("time", new Date());
        mvc.addObject("message", this.message);
        mvc.setViewName("welcome");
        return mvc;
    }

    @RequestMapping("/foo")
    public String foo(Map<String, Object> model) {
        throw new RuntimeException("Foo");
    }
}
