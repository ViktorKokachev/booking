package com.webapp.booking.repos;

import com.webapp.booking.enums.RoomType;
import com.webapp.booking.repos.util.RoomEntityRowMapper;
import com.webapp.booking.entities.RoomEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class RoomRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RoomEntityRowMapper rowMapper;

    public List<RoomEntity> getDiscountRooms(int amount) {
        String sql = "SELECT room_id, number, guest_amount, room_type, price, room.description, discount, "
                + "room.hotel_id, name, address, rating, hotel.description, owner_id, is_approved "
                + "FROM room JOIN hotel ON room.hotel_id = hotel.hotel_id "
                + "WHERE discount IS NOT NULL ORDER BY rand() LIMIT " + amount;
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<RoomEntity> getAllRooms() {
        String sql = "select room_id, number, guest_amount, room_type, price, room.description, discount, "
                + "room.hotel_id, name, address, rating, hotel.description, owner_id, is_approved "
                + "FROM room JOIN hotel ON room.hotel_id = hotel.hotel_id";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<RoomEntity> getAllRoomsWithFilters(Date checkInDate, Date checkOutDate, Integer guestAmount, Integer hotelRating, Double minPrice, Double maxPrice, RoomType roomType) {

        String sql = "select r.room_id, r.number, r.guest_amount, r.room_type, r.price, r.description, r.hotel_id, r.discount "
                + "from (select room_id, "
                + "sum(case when (('"+ new java.sql.Date(checkInDate.getTime()) + "' < check_in and '" + new java.sql.Date(checkInDate.getTime()) + "' < check_in) "
                + "or ('"+ new java.sql.Date(checkOutDate.getTime()) + "' > check_out and '"+ new java.sql.Date(checkOutDate.getTime()) + "' > check_out)) "
                + "then 1 "
                + "else 0 "
                + "end) as tpk "
                + "from request "
                + "group by room_id "
                + "having tpk = 0) as tmp "
                + "join room r on (r.room_id = tmp.room_id) "
                + "join hotel h on (r.hotel_id = h.hotel_id) "
                + "where tpk = 0 ";

        if (hotelRating != null) {
            sql += "and h.rating = " + hotelRating + " ";
        }
        if (roomType != null) {
            sql+= "and r.room_type = '" + roomType + "' ";
        }
        if (guestAmount != null) {
            sql += "and r.guest_amount = " + guestAmount + " ";
        }
        if (maxPrice != null) {
            sql += "and (r.price <= " + maxPrice + " or r.discount <= " + maxPrice + " ) ";
        }
        if (minPrice != null) {
            sql += "and ((r.price >= " + minPrice + " and r.discount is null) or (r.discount is not null and r.discount >= " + minPrice + ")) ";
        }

        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<RoomEntity> getAllRoomsWithFiltersWithoutDates(Integer guestAmount, Integer hotelRating, Double minPrice, Double maxPrice, RoomType roomType) {
        String sql = "SELECT r.room_id, r.number, r.guest_amount, r.room_type, r.price, r.description, r.hotel_id, r.discount\n"
                + "FROM room r "
                + "JOIN hotel h on (r.hotel_id = h.hotel_id) "
                + "WHERE";

        if (hotelRating != null) {
            sql += " h.rating = " + hotelRating + " AND";
        }
        if (roomType != null) {
            sql+= " r.room_type = '" + roomType + "' AND";
        }
        if (guestAmount != null) {
            sql += " r.guest_amount = " + guestAmount + " AND";
        }
        if (maxPrice != null) {
            sql += " (r.price <= " + maxPrice + " or r.discount <= " + maxPrice + " AND)";
        }
        if (minPrice != null) {
            sql += " ((r.price >= " + minPrice + " AND r.discount is null) OR (r.discount is not null AND r.discount >= " + minPrice + "))";
        }

        if (sql.endsWith("WHERE") || sql.endsWith("AND")) {
            sql = sql.substring(0, sql.lastIndexOf(" "));
        }

        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<RoomEntity> getRoomByID(Integer roomID) {
        String sql = "select room_id, number, guest_amount, room_type, price, room.description, discount, "
                + "room.hotel_id, name, address, rating, hotel.description, owner_id, is_approved "
                + "FROM room JOIN hotel ON room.hotel_id = hotel.hotel_id "
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
                + roomEntity.getHotel().getHotelID() + "', '"
                + roomEntity.getDiscount() + "')";
        jdbcTemplate.execute(sql);
    }

    public void updateRoom(RoomEntity roomEntity) {
        String sql = "UPDATE room SET "
                + "number = '" + roomEntity.getNumber()
                + "', guest_amount = '" + roomEntity.getGuestAmount()
                + "', room_type = '" + roomEntity.getRoomType()
                + "', price = '" + roomEntity.getPrice()
                + "', description = '" + roomEntity.getDescription()
                + "', hotel_id = '" + roomEntity.getHotel().getHotelID()
                + "', discount = '" + roomEntity.getDiscount()
                + "' WHERE room_id = " + roomEntity.getRoomID();
        jdbcTemplate.execute(sql);
    }

    public void deleteRoom(Integer roomID) {
        String sql = "DELETE FROM room WHERE room_id = " + roomID ;
        jdbcTemplate.execute(sql);
    }
}
