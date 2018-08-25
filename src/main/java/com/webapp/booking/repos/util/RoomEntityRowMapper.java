package com.webapp.booking.repos.util;

import com.webapp.booking.entities.HotelEntity;
import com.webapp.booking.entities.RoomEntity;
import com.webapp.booking.enums.RoomType;
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
        Double discount = rs.getDouble("discount");
        String roomDescription = rs.getString("room.description");
        String roomType = rs.getString("room_type");
        Double price = rs.getDouble("price");

        Integer hotelID = rs.getInt("hotel_id");
        String name = rs.getString("name");
        String address = rs.getString("address");
        Integer rating = rs.getInt("rating");
        String hotelDescription = rs.getString("hotel.description");
        Integer ownerID = rs.getInt("owner_id");
        Boolean isApproved = rs.getBoolean("is_approved");

        HotelEntity hotelEntity = new HotelEntity();

        hotelEntity.setHotelID(hotelID);
        hotelEntity.setName(name);
        hotelEntity.setAddress(address);
        hotelEntity.setRating(rating);
        hotelEntity.setDescription(hotelDescription);
        hotelEntity.setOwnerID(ownerID);
        hotelEntity.setIsApproved(isApproved);

        RoomEntity roomEntity = new RoomEntity();

        roomEntity.setRoomID(roomId);
        roomEntity.setNumber(number);
        roomEntity.setGuestAmount(guestAmount);
        roomEntity.setHotel(hotelEntity);
        roomEntity.setDiscount(discount);
        roomEntity.setDescription(roomDescription);
        roomEntity.setRoomType(RoomType.valueOf(roomType));
        roomEntity.setPrice(price);

        return roomEntity;
    }
}
