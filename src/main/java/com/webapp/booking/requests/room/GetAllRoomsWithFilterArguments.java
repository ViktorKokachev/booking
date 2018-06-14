package com.webapp.booking.requests.room;

import com.webapp.booking.enums.RoomType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetAllRoomsWithFilterArguments {
    private Integer guestAmount;
    private RoomType roomType;
    private Double minPrice;
    private Double maxPrice;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
}
