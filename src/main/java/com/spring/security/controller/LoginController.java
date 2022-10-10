package com.spring.security.controller;

import com.spring.security.dao.SignupDao;
import com.spring.security.model.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SignupDao signupDao;

    @GetMapping("/myCustomLogin")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(@ModelAttribute("signup") SignupDTO signup) {
        return "signup";
    }

    @PostMapping("/process-signup")
    public String processSignup(SignupDTO signup) {
        String encodedPassword = passwordEncoder.encode(signup.getPassword());
        signup.setPassword(encodedPassword);
        signupDao.saveUser(signup);
        return "redirect:/myCustomLogin";
    }


}
