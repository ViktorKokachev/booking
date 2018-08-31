package com.webapp.booking.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HotelEntity {
    private Integer hotelID;
    private String name;
    private String address;
    private Integer rating;
    private String description;
    private UserEntity owner;
    private Boolean isApproved;
}
