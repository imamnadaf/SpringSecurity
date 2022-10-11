package com.spring.security.dao;

import com.spring.security.model.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SignupDaoImpl implements SignupDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void saveUser( SignupDTO signup) {
        String sql = "insert into users values(?, ?, ?)";
        String sql2 = "insert into authorities values(?, ?)";
        jdbcTemplate.update(sql, signup.getUsername(), signup.getPassword(), 1);
        jdbcTemplate.update(sql, ps -> {
            ps.setString(1, signup.getUsername());
            ps.setString(2, "USER");
        });
//        jdbcTemplate.update(sql2, signup.getUsername(), "USER");
    }
}
