package com.spring.security.controller;

import com.spring.security.dao.SignupDao;
import com.spring.security.model.ChangePasswordDTO;
import com.spring.security.model.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class LoginController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SignupDao signupDao;

    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;

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

//        To store using Jdbc manager
        UserDetails user = User.withUsername(signup.getUsername()).password(signup.getPassword()).authorities("USER").build();
        jdbcUserDetailsManager.createUser(user );

//        To storedata using dao
//        signupDao.saveUser(signup);
        return "redirect:/myCustomLogin";
    }


    @GetMapping("/change-password")
    public String changePassword(@ModelAttribute("change-password") ChangePasswordDTO changePassword) {
        return "change-password";
    }

    @PostMapping("/process-change-password")
    public String processChangePassword(Principal principal, ChangePasswordDTO changePassword) {

        UserDetails userDetails = jdbcUserDetailsManager.loadUserByUsername(principal.getName());
        boolean matches = passwordEncoder.matches(changePassword.getOldPassword(), userDetails.getPassword());
        if (matches) {
//        To store using Jdbc manager
            String encodedPassword = passwordEncoder.encode(changePassword.getConfirmPassword());
            jdbcUserDetailsManager.changePassword(changePassword.getOldPassword(), encodedPassword);
            return "redirect:/home";
        }

//        To storedata using dao
//        signupDao.saveUser(signup);
        return "redirect:/change-password?invalidPassword";
    }




}
