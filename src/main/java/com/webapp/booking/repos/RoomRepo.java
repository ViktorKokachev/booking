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

        String sql = "select room.room_id, room.number, room.guest_amount, room.room_type, room.price, room.description, room.hotel_id, room.discount, "
                + "name, address, rating, hotel.description, owner_id, is_approved "
                + "from room join hotel on room.hotel_id = hotel.hotel_id "
                + "where room.room_id not in "
                + "(select room_id from "
                + "(select request.room_id, "
                + "sum(case when (('"+ new java.sql.Date(checkInDate.getTime()) + "' < check_in and '" + new java.sql.Date(checkOutDate.getTime()) + "' < check_in) "
                + "or ('"+ new java.sql.Date(checkInDate.getTime()) + "' > check_out and '"+ new java.sql.Date(checkOutDate.getTime()) + "' > check_out)) "
                + "then 0 "
                + "else 1 "
                + "end) as tpk "
                + "from request "
                + "group by request.room_id "
                + "having tpk > 0) as tmp) ";

        if (hotelRating != null) {
            sql += "and rating = " + hotelRating + " ";
        }
        if (roomType != null) {
            sql+= "and room_type = '" + roomType + "' ";
        }
        if (guestAmount != null) {
            sql += "and guest_amount = " + guestAmount + " ";
        }
        if (maxPrice != null) {
            sql += "and (price <= " + maxPrice + " or discount <= " + maxPrice + " ) ";
        }
        if (minPrice != null) {
            sql += "and ((price >= " + minPrice + " and discount is null) or (discount is not null and discount >= " + minPrice + ")) ";
        }


        System.err.println(sql);

        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<RoomEntity> getAllRoomsWithFiltersWithoutDates(Integer guestAmount, Integer hotelRating, Double minPrice, Double maxPrice, RoomType roomType) {
        String sql = "SELECT room_id, number, guest_amount, room_type, price, room.description, room.hotel_id, discount, "
                + "name, address, rating, hotel.description, owner_id, is_approved "
                + "FROM room "
                + "JOIN hotel on (room.hotel_id = hotel.hotel_id) "
                + "WHERE";

        if (hotelRating != null) {
            sql += " rating = " + hotelRating + " AND";
        }
        if (roomType != null) {
            sql+= " room_type = '" + roomType + "' AND";
        }
        if (guestAmount != null) {
            sql += " guest_amount = " + guestAmount + " AND";
        }
        if (maxPrice != null) {
            sql += " (price <= " + maxPrice + " or discount <= " + maxPrice + " AND)";
        }
        if (minPrice != null) {
            sql += " ((price >= " + minPrice + " AND discount is null) OR (discount is not null AND discount >= " + minPrice + "))";
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
