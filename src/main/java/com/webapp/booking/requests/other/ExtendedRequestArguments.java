package com.webapp.booking.requests.other;

import com.webapp.booking.enums.RoomType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class ExtendedRequestArguments {
    private Integer roomID;
    private Integer guestAmount;
    private RoomType roomType;
    private Double price;
    private Double discount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkInDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkOutDate;
    private String roomDescription;
    private String hotelName;
    private String hotelAddress;
    private String hotelDescription;
    private Integer hotelRating;
}
