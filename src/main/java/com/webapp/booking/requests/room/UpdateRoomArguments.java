package com.webapp.booking.requests.room;

import com.webapp.booking.enums.RoomType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRoomArguments {
    private Integer roomID;
    private Integer number;
    private Integer guestAmount;
    private RoomType roomType;
    private Double price;
    private String description;
    private Integer hotelID;
}
