package com.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hellow";
    }

    @ResponseBody
    @GetMapping("/hello-test")
    public String hello_test() {
        return "This is spring security hello";
    }

    @ResponseBody
    @GetMapping("/bye")
    public String bye() {
        return "Going bye";
    }

}
