package com.webapp.booking.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelEntity {
    private Integer hotelID;
    private String name;
    private String address;
    private Integer rating;
    private String description;
    private Integer ownerID;
}
