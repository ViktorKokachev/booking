package com.webapp.booking.requests.hotel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateHotelArguments {
    private String name;
    private String address;
    private Integer rating;
    private String description;
    private Integer ownerID;
    private Boolean isApproved;
}
