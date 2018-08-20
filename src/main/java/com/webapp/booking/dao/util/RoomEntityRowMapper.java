package com.webapp.booking.dao.util;

import com.webapp.booking.entities.RoomEntity;
import com.webapp.booking.enums.RoomType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomEntityRowMapper implements RowMapper<RoomEntity> {

    public RoomEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer roomId = rs.getInt("room_id");
        Integer number = rs.getInt("number");
        Integer guestAmount = rs.getInt("guest_amount");
        Integer hotelId = rs.getInt("hotel_id");
        Double discount = rs.getDouble("discount");
        String description = rs.getString("description");
        String roomType = rs.getString("room_type");

        RoomEntity roomEntity = new RoomEntity();

        roomEntity.setRoomID(roomId);
        roomEntity.setNumber(number);
        roomEntity.setGuestAmount(guestAmount);
        roomEntity.setHotelID(hotelId);
        roomEntity.setDiscount(discount);
        roomEntity.setDescription(description);
        roomEntity.setRoomType(RoomType.valueOf(roomType));

        return roomEntity;
    }
}
