package com.webapp.booking.repos.util;

import com.webapp.booking.entities.RequestEntity;
import com.webapp.booking.enums.RequestStatus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RequestEntityRowMapper implements RowMapper<RequestEntity> {
    @Override
    public RequestEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

        Date checkInDate = rs.getDate("check_in");
        Date checkOutDate = rs.getDate("check_out");
        Date requestDate = rs.getDate("request_date");
        Integer requestID = rs.getInt("request_id");
        String requestStatus = rs.getString("status");
        Integer roomID = rs.getInt("room_id");
        Integer userID = rs.getInt("user_id");

        RequestEntity requestEntity = new RequestEntity();

        requestEntity.setCheckInDate(checkInDate);
        requestEntity.setCheckOutDate(checkOutDate);
        requestEntity.setRequestDate(requestDate);
        requestEntity.setRequestID(requestID);
        requestEntity.setRequestStatus(RequestStatus.valueOf(requestStatus));
        requestEntity.setRoomID(roomID);
        requestEntity.setUserID(userID);

        return requestEntity;
    }
}
