package com.webapp.booking.requests.room;

import com.webapp.booking.enums.RoomType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class GetAllRoomsWithFilterArguments {
    private Integer guestAmount;
    private RoomType roomType;
    private Double minPrice;
    private Double maxPrice;
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private Date checkInDate;
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private Date checkOutDate;
}
