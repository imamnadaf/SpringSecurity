package com.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder bcryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("imam")
                .password("$2a$12$mA8jbKE5flcDY.njWuzLwuNNxz5IaDOQzg2hXDDV6ncj2w.qSV1Me")
                .roles("admin");

        System.out.println("Password: "+bcryptPasswordEncoder.encode("imam@123"));

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/myCustomLogin").permitAll()
                .anyRequest()
//                .denyAll()
//                .permitAll()
                .authenticated()
                .and()
                .formLogin().loginPage("/myCustomLogin")
                .and()
                .httpBasic();

    }
}
