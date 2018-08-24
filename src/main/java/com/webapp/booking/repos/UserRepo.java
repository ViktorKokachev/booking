package com.webapp.booking.repos;

import com.webapp.booking.entities.UserEntity;
import com.webapp.booking.repos.util.UserEntityRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserEntityRowMapper userEntityRowMapper;

    public List<UserEntity> getAllUsers() {
        String sql = "SELECT user_id, login, password, name, role FROM user";
        return jdbcTemplate.query(sql, userEntityRowMapper);
    }

    public List<UserEntity> getUserByID(Integer userID) {
        String sql = "SELECT user_id, login, password, name, role FROM user WHERE user_id = " + userID;
        return jdbcTemplate.query(sql, userEntityRowMapper);
    }
}
