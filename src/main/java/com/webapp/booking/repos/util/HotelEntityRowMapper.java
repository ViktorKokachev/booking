package com.webapp.booking.repos.util;

import com.webapp.booking.entities.HotelEntity;
import com.webapp.booking.entities.UserEntity;
import com.webapp.booking.enums.UserRole;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class HotelEntityRowMapper implements RowMapper<HotelEntity> {
    @Override
    public HotelEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

        Integer hotelID = rs.getInt("hotel_id");
        String address = rs.getString("address");
        String description = rs.getString("description");
        Boolean isApproved = rs.getBoolean("is_approved");
        String name = rs.getString("name");
        Integer rating = rs.getInt("rating");

        HotelEntity hotelEntity = new HotelEntity();

        String login = rs.getString("login");
        String userName = rs.getString("user.name");
        Integer userID = rs.getInt("user_id");
        String password = rs.getString("password");
        String role = rs.getString("role");

        UserEntity ownerEntity = new UserEntity();

        ownerEntity.setLogin(login);
        ownerEntity.setName(userName);
        ownerEntity.setUserID(userID);
        ownerEntity.setPassword(password);
        ownerEntity.setUserRole(UserRole.valueOf(role));

        hotelEntity.setHotelID(hotelID);
        hotelEntity.setAddress(address);
        hotelEntity.setDescription(description);
        hotelEntity.setIsApproved(isApproved);
        hotelEntity.setName(name);
        hotelEntity.setOwner(ownerEntity);
        hotelEntity.setRating(rating);

        return hotelEntity;
    }
}
