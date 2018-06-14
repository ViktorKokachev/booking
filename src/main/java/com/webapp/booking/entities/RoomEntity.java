package com.webapp.booking.entities;

import com.webapp.booking.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
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
