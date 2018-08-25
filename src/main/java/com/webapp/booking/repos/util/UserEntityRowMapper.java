package com.webapp.booking.repos.util;

import com.webapp.booking.entities.UserEntity;
import com.webapp.booking.enums.RoomType;
import com.webapp.booking.enums.UserRole;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserEntityRowMapper implements RowMapper<UserEntity> {
    @Override
    public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

        String login = rs.getString("login");
        String name = rs.getString("name");
        Integer userID = rs.getInt("user_id");
        String password = rs.getString("password");
        String role = rs.getString("role");

        UserEntity userEntity = new UserEntity();

        userEntity.setLogin(login);
        userEntity.setName(name);
        userEntity.setUserID(userID);
        userEntity.setPassword(password);
        userEntity.setUserRole(UserRole.valueOf(role));

        return userEntity;
    }
}
