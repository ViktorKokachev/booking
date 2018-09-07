package com.webapp.booking.repos;

import com.webapp.booking.entities.UserEntity;
import com.webapp.booking.enums.UserRole;
import com.webapp.booking.repos.util.UserEntityRowMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserRepo {

    JdbcTemplate jdbcTemplate;

    UserEntityRowMapper userEntityRowMapper;

    public List<UserEntity> getAllUsers() {
        String sql = "SELECT user_id, login, password, name, role FROM user";
        return jdbcTemplate.query(sql, userEntityRowMapper);
    }

    public List<UserEntity> getUserByID(Integer userID) {
        String sql = "SELECT user_id, login, password, name, role FROM user WHERE user_id = " + userID;
        return jdbcTemplate.query(sql, userEntityRowMapper);
    }

    public void deleteUser(Integer userID) {
        String sql = "DELETE FROM user WHERE user_id = " + userID;
        jdbcTemplate.execute(sql);
    }

    public void createUser(UserEntity userEntity) {
        String sql = "INSERT INTO user (login, password, name, role) VALUES ('"
                + userEntity.getLogin() + "', '"
                + userEntity.getPassword() + "', '"
                + userEntity.getName() + "', '"
                + userEntity.getUserRole() + "')";
        jdbcTemplate.execute(sql);
    }

    public void updateUser(UserEntity userEntity) {
        String sql = "UPDATE user SET "
                + "login = '" + userEntity.getLogin()
                + "', password = '" + userEntity.getPassword()
                + "', name = '" + userEntity.getName()
                + "', role = '" + userEntity.getUserRole()
                + "' WHERE user_id = " + userEntity.getUserID();
        jdbcTemplate.execute(sql);
    }

    public List<UserEntity> getUserByLogin(String username) {
        String sql = "SELECT user_id, login, password, name, role FROM user WHERE login = '" + username + "'";
        return jdbcTemplate.query(sql, userEntityRowMapper);
    }

    public List<UserEntity> getAllUsersWithFilter(String login, String name, UserRole userRole) {
        String sql = "SELECT user_id, login, password, name, role FROM user ";

        if (login == null && name == null && userRole == null)
            return jdbcTemplate.query(sql, userEntityRowMapper);

        sql += "WHERE";

        if (login != null) {
            sql += " login = '" + login + "' AND";
        }
        if (name != null) {
            sql += " name = '" + name + "' AND";
        }
        if (userRole != null) {
            sql += " role = '" + userRole + "'";
        }

        if (sql.endsWith("WHERE") || sql.endsWith("AND")) {
            sql = sql.substring(0, sql.lastIndexOf(" "));
        }

        return jdbcTemplate.query(sql, userEntityRowMapper);
    }
}
