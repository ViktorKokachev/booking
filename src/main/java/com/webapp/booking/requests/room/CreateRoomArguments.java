package com.webapp.booking.requests.room;

import com.webapp.booking.enums.RoomType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoomArguments {
    private Integer number;
    private Integer guestAmount;
    private RoomType roomType;
    private Double price;
    private Double discount;
    private String description;
    private Integer hotelID;
}
