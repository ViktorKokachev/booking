package com.webapp.booking.entities;

import com.webapp.booking.enums.RoomType;

public class RoomEntity {
    private Integer roomID;
    private Integer number;
    private Integer guestAmount;
    private RoomType roomType;
    private Double price;
    private Double discount;
    private String description;
    private Integer hotelID;
}
