package com.spring.security.dao;

import com.spring.security.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Customer findCustomerByUserName(String username) {
        String sql = "select * from customer where username=?";
        Object[] args = {username};
        List<Customer> customerList =  jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<Customer>(Customer.class));
        return customerList.isEmpty() ? null: customerList.get(0);
    }
}
