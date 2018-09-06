package com.webapp.booking.repos.util;

import com.webapp.booking.entities.HotelEntity;
import com.webapp.booking.entities.RequestEntity;
import com.webapp.booking.entities.RoomEntity;
import com.webapp.booking.entities.UserEntity;
import com.webapp.booking.enums.RequestStatus;
import com.webapp.booking.enums.RoomType;
import com.webapp.booking.enums.UserRole;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RequestEntityRowMapper implements RowMapper<RequestEntity> {
    @Override
    public RequestEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

        Integer roomId = rs.getInt("room.room_id");
        Integer number = rs.getInt("number");
        Integer guestAmount = rs.getInt("guest_amount");
        Double discount = rs.getDouble("discount");
        String roomDescription = rs.getString("room.description");
        String roomType = rs.getString("room_type");
        Double price = rs.getDouble("price");

        Integer hotelID = rs.getInt("hotel_id");
        String name = rs.getString("hotel.name");
        String address = rs.getString("address");
        Integer rating = rs.getInt("rating");
        String hotelDescription = rs.getString("hotel.description");
        Integer ownerID = rs.getInt("owner_id");

        HotelEntity hotelEntity = new HotelEntity();

        hotelEntity.setHotelID(hotelID);
        hotelEntity.setName(name);
        hotelEntity.setAddress(address);
        hotelEntity.setRating(rating);
        hotelEntity.setDescription(hotelDescription);
        hotelEntity.setOwnerID(ownerID);

        RoomEntity roomEntity = new RoomEntity();

        roomEntity.setRoomID(roomId);
        roomEntity.setNumber(number);
        roomEntity.setGuestAmount(guestAmount);
        roomEntity.setHotel(hotelEntity);
        roomEntity.setDiscount(discount);
        roomEntity.setDescription(roomDescription);
        roomEntity.setRoomType(RoomType.valueOf(roomType));
        roomEntity.setPrice(price);

        UserEntity userEntity = new UserEntity();

        String login = rs.getString("login");
        String userName = rs.getString("user.name");
        Integer userID = rs.getInt("user_id");
        String password = rs.getString("password");
        String role = rs.getString("role");

        userEntity.setLogin(login);
        userEntity.setName(userName);
        userEntity.setUserID(userID);
        userEntity.setPassword(password);
        userEntity.setUserRole(UserRole.valueOf(role));

        Date checkInDate = rs.getDate("check_in");
        Date checkOutDate = rs.getDate("check_out");
        Date requestDate = rs.getDate("request_date");
        Integer requestID = rs.getInt("request_id");
        String requestStatus = rs.getString("status");

        RequestEntity requestEntity = new RequestEntity();

        requestEntity.setCheckInDate(checkInDate);
        requestEntity.setCheckOutDate(checkOutDate);
        requestEntity.setRequestDate(requestDate);
        requestEntity.setRequestID(requestID);
        requestEntity.setRequestStatus(RequestStatus.valueOf(requestStatus));
        requestEntity.setRoom(roomEntity);
        requestEntity.setUser(userEntity);

        return requestEntity;
    }
}
