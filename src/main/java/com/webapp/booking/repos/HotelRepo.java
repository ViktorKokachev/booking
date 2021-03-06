package com.webapp.booking.repos;

import com.webapp.booking.entities.HotelEntity;
import com.webapp.booking.repos.util.HotelEntityRowMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HotelRepo {

    private JdbcTemplate jdbcTemplate;
    private HotelEntityRowMapper rowMapper;

    public List<HotelEntity> getAllHotels() {
        String sql = "SELECT hotel_id, name, address, rating, description, owner_id FROM hotel";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<HotelEntity> getAllHotelsByOwnerID(Integer ownerID) {
        String sql = "SELECT hotel_id, name, address, rating, description, owner_id FROM hotel WHERE owner_id = " + ownerID;
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<HotelEntity> getHotelByID(Integer hotelID) {
        String sql = "SELECT hotel_id, name, address, rating, description, owner_id FROM hotel WHERE hotel_id = " + hotelID;
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void createHotel(HotelEntity hotelEntity) {
        String sql = "INSERT INTO hotel (name, address, rating, description, owner_id) VALUES ('"
                + hotelEntity.getName() + "', '"
                + hotelEntity.getAddress() + "', '"
                + hotelEntity.getRating() + "', '"
                + hotelEntity.getDescription() + "', '"
                + hotelEntity.getOwnerID() + "')";
        jdbcTemplate.execute(sql);
    }

    public void updateHotel(HotelEntity hotelEntity) {
        String sql = "UPDATE hotel SET "
                + "name = '" + hotelEntity.getName()
                + "', address = '" + hotelEntity.getAddress()
                + "', rating = '" + hotelEntity.getRating()
                + "', description = '" + hotelEntity.getDescription()
                + "', owner_id = '" + hotelEntity.getOwnerID()
                + "' WHERE hotel_id = " + hotelEntity.getHotelID();
        jdbcTemplate.execute(sql);
    }

    public void deleteHotel(Integer hotelID) {
        String sql = "DELETE FROM hotel WHERE hotel_id = " + hotelID;
        jdbcTemplate.execute(sql);
    }

    public List<HotelEntity> getAllHotelsWithFilter(String name, Integer rating) {
        String sql = "SELECT hotel_id, name, address, rating, description, owner_id FROM hotel ";

        if (name.isEmpty() && rating == null) {
            return jdbcTemplate.query(sql, rowMapper);

        }

        sql += "WHERE";

        if (!name.isEmpty()) {
            sql += " name = '" + name + "' AND";
        }
        if (rating != null) {
            sql += " rating = '" + rating + "'";
        }

        if (sql.endsWith("WHERE") || sql.endsWith("AND")) {
            sql = sql.substring(0, sql.lastIndexOf(" "));
        }

        return jdbcTemplate.query(sql, rowMapper);
    }
}
