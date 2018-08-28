package com.webapp.booking.repos;

import com.webapp.booking.entities.RequestEntity;
import com.webapp.booking.repos.util.RequestEntityRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RequestRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RequestEntityRowMapper requestEntityRowMapper;

    public List<RequestEntity> getAllRequests() {
        String sql = "SELECT request_id, check_in, check_out, status, user_id, room_id, request_date FROM request";
        return jdbcTemplate.query(sql, requestEntityRowMapper);
    }

    public List<RequestEntity> getAllRequestsByUserID(Integer userID) {
        String sql = "SELECT request_id, check_in, check_out, status, user_id, room_id, request_date FROM request "
                + "WHERE user_id = " + userID;
        return jdbcTemplate.query(sql, requestEntityRowMapper);
    }

    public List<RequestEntity> getRequestByID(Integer requestID) {
        String sql = "SELECT request_id, check_in, check_out, status, user_id, room_id, request_date FROM request "
                + "WHERE request_id = " + requestID;
        return jdbcTemplate.query(sql, requestEntityRowMapper);
    }

    public void deleteRequest(Integer requestID) {
        String sql = "DELETE FROM request WHERE request_id = " + requestID;
        jdbcTemplate.execute(sql);
    }

    public void createRequest(RequestEntity requestEntity) {
        String sql = "INSERT INTO request (room_id, user_id, check_in, check_out, request_date) VALUES ('"
                + requestEntity.getRoomID() + "', '"
                + requestEntity.getUserID() + "', '"
                + new java.sql.Date(requestEntity.getCheckInDate().getTime()) + "', '"
                + new java.sql.Date(requestEntity.getCheckOutDate().getTime()) + "', "
                + "now())";
        jdbcTemplate.execute(sql);
    }

    public void updateRequest(RequestEntity requestEntity) {
        String sql = "UPDATE request SET "
                + "check_in = '" + requestEntity.getCheckInDate()
                + "', check_out = '" + requestEntity.getCheckOutDate()
                + "', status = '" + requestEntity.getRequestStatus()
                + "' WHERE room_id = " + requestEntity.getRoomID();
        jdbcTemplate.execute(sql);
    }

    public void payRequest(Integer requestID) {
        String sql = "UPDATE request SET status = 'PAYED' WHERE request_id = " + requestID;
        jdbcTemplate.execute(sql);
    }

    public void rejectRequest(Integer requestID) {
        String sql = "UPDATE request SET status = 'REJECTED' WHERE request_id = " + requestID;
        jdbcTemplate.execute(sql);
    }
}
