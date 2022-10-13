package com.spring.security.config;

import com.spring.security.service.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import sun.plugin2.applet.context.NoopExecutionContext;

import javax.sql.DataSource;

@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    DataSource dataSource;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CustomerUserDetailsService customerUserDetailsService;

    public static final String DEF_USERS_BY_USERNAME_QUERY = "select username,password,enabled "
            + "from customer " + "where username = ?";
    public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY = "select username,roles "
            + "from customer " + "where username = ?";
    public static final String DEF_CREATE_USER_SQL = "insert into customer (username, password, enabled) values (?,?,?)";
    public static final String DEF_INSERT_AUTHORITY_SQL = "update customer set roles = ? where username=?";



    // To use email as username

//    public static final String DEF_USERS_BY_USERNAME_QUERY = "select email,password,enabled "
//            + "from customer " + "where email = ?";
//    public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY = "select email,roles "
//            + "from customer " + "where email = ?";

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(DEF_USERS_BY_USERNAME_QUERY)
//                .authoritiesByUsernameQuery(DEF_AUTHORITIES_BY_USERNAME_QUERY)
//                .passwordEncoder(passwordEncoder);
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        UserDetails imamUser = User.withUsername("imam").password("imam1234").roles("ADMIN", "USER").build();
//        UserDetails aliUser = User.withUsername("ali").password("ali234").roles("USER").build();
//        userDetailsManager.createUser(imamUser);
//        userDetailsManager.createUser(aliUser);
//
//        auth
//                .userDetailsService(userDetailsManager);
//    }

    // For custom JdbcUserDetailsManager

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(jdbcUserDetailsManager)
//                .passwordEncoder(passwordEncoder);
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customerUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/myCustomLogin").permitAll()
                .antMatchers("/signup", "/process-signup").permitAll()
                .antMatchers("/trainer").hasAuthority("Trainer")
                .antMatchers("/coder").hasAuthority("Coder")
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
