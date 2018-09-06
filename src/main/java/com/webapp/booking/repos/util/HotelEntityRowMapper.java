package com.webapp.booking.repos.util;

import com.webapp.booking.entities.HotelEntity;
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
        String descrption = rs.getString("description");
        String name = rs.getString("name");
        Integer ownerID = rs.getInt("owner_id");
        Integer rating = rs.getInt("rating");

        HotelEntity hotelEntity = new HotelEntity();

        hotelEntity.setHotelID(hotelID);
        hotelEntity.setAddress(address);
        hotelEntity.setDescription(descrption);
        hotelEntity.setName(name);
        hotelEntity.setOwnerID(ownerID);
        hotelEntity.setRating(rating);

        return hotelEntity;
    }
}
