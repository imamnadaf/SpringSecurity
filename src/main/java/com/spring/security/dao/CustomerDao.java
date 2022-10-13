package com.spring.security.dao;

import com.spring.security.model.Customer;

public interface CustomerDao {
    Customer findCustomerByUserName(String username);
}
