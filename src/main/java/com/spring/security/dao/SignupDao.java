package com.spring.security.dao;

import com.spring.security.model.SignupDTO;

public interface SignupDao {
    void saveUser(SignupDTO student);
}
