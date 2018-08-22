package com.webapp.booking.repos;

import com.webapp.booking.repos.util.RoomEntityRowMapper;
import com.webapp.booking.entities.RoomEntity;
import com.webapp.booking.requests.room.GetAllRoomsWithFilterArguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RoomEntityRowMapper rowMapper;

    /*@Autowired
    public RoomRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }*/

    public List<RoomEntity> getDiscountRooms(int amount) {
        String sql = "select room_id, number, guest_amount, room_type, price, description, hotel_id, discount from room "
                + "where discount is not null order by rand() limit " + amount;
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<RoomEntity> getAllRooms() {
        String sql = "select room_id, number, guest_amount, room_type, price, description, hotel_id, discount from room";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<RoomEntity> getAllRoomsWithFilters(GetAllRoomsWithFilterArguments getAllRoomsWithFilterArguments) {
        String sql = "select room_id, number, guest_amount, room_type, price, description, hotel_id, discount from room r"
                + "join hotel h on r.hotel_id = h.hotel_id "
                + "where h.rating  =  AND r.room_type = AND r.guest_amount = AND r.check_in = AND r.checkout AND price";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<RoomEntity> getRoomByID(Integer roomID) {
        String sql = "select room_id, number, guest_amount, room_type, price, description, hotel_id, discount from room"
                + "where room_id = " + roomID;
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void createRoom(RoomEntity roomEntity) {
        String sql = "INSERT INTO room (number, guest_amount, room_type, price, description, hotel_id, discount) VALUES ('"
                + roomEntity.getNumber() + "', '"
                + roomEntity.getGuestAmount() + "', '"
                + roomEntity.getRoomType() + "', '"
                + roomEntity.getPrice() + "', '"
                + roomEntity.getDescription() + "', '"
                + roomEntity.getHotelID() + "', '"
                + roomEntity.getDiscount() + "')";
        jdbcTemplate.execute(sql);
    }

    public void updateRoom(RoomEntity roomEntity) {
        String sql = "UPDATE room  SET "
                + "number = '" + roomEntity.getNumber()
                + "', guest_amount = '" + roomEntity.getGuestAmount()
                + "', room_type = '" + roomEntity.getRoomType()
                + "', price = '" + roomEntity.getPrice()
                + "', description = '" + roomEntity.getDescription()
                + "', hotel_id = '" + roomEntity.getHotelID()
                + "', discount = '" + roomEntity.getDiscount()
                + "' WHERE room_id = " + roomEntity.getRoomID();
        jdbcTemplate.execute(sql);
    }
}
