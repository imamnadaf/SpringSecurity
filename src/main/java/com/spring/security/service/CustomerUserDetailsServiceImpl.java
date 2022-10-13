package com.spring.security.service;

import com.spring.security.dao.CustomerDao;
import com.spring.security.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerUserDetailsServiceImpl implements CustomerUserDetailsService {

    @Autowired
    CustomerDao customerDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerDao.findCustomerByUserName(username);
        if (customer == null)
                throw  new UsernameNotFoundException("Username: "+username+" not found");
//        To create custom authorities
//        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
//        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(customer.getRoles());
//        authorities.add(authority);
        return User
                .withUsername(customer.getUsername())
                .password(customer.getPassword())
                .authorities(customer.getRoles())
                .build();
    }
}
