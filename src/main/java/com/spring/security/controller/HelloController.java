package com.spring.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Collection;

@Controller
public class HelloController {


    @GetMapping("/home")
    public String home(Principal principal, Authentication authentication, Model model) {
        //Fetching the username
        String userName = principal.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        model.addAttribute("username", userName);
        model.addAttribute("authorities", authorities);
        return "home";
    }

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

    @GetMapping("/coder")
    public String coder() {
        return "coder-dashboard";
    }

    @GetMapping("/trainer")
    public String trainer() {
        return "trainer-dashboard";
    }



}
