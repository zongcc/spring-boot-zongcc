package com.zongcc.boot.controller;

import com.zongcc.boot.exception.SpringBootException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chunchengzong on 2017-01-06.
 */
//@EnableAutoConfiguration
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/e")
    public String getException() throws Exception {
        throw new Exception("exception------------");
    }

    @PostMapping("/s")
    public String getSpringBootException() {
        throw new SpringBootException("SpringBootException------------");
    }

}