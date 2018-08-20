package com.webapp.booking.dao;

import com.webapp.booking.dao.util.RoomEntityRowMapper;
import com.webapp.booking.entities.RoomEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<RoomEntity> rowMapper = new RoomEntityRowMapper();

    @Autowired
    public RoomRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<RoomEntity> getAllRooms() {
        String sql = "select room_id, number, guest_amount, room_type, description, hotel_id, discount from room";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
