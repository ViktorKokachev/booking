package com.webapp.booking.repos.util;

import com.webapp.booking.entities.RoomEntity;
import com.webapp.booking.enums.RoomType;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RoomEntityRowMapper implements RowMapper<RoomEntity> {

    public RoomEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer roomId = rs.getInt("room_id");
        Integer number = rs.getInt("number");
        Integer guestAmount = rs.getInt("guest_amount");
        Integer hotelId = rs.getInt("hotel_id");
        Double discount = rs.getDouble("discount");
        String description = rs.getString("description");
        String roomType = rs.getString("room_type");
        Double price = rs.getDouble("price");

        RoomEntity roomEntity = new RoomEntity();

        roomEntity.setRoomID(roomId);
        roomEntity.setNumber(number);
        roomEntity.setGuestAmount(guestAmount);
        roomEntity.setHotelID(hotelId);
        roomEntity.setDiscount(discount);
        roomEntity.setDescription(description);
        roomEntity.setRoomType(RoomType.valueOf(roomType));
        roomEntity.setPrice(price);

        return roomEntity;
    }
}
