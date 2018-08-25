package com.webapp.booking.requests.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CreateRequestArguments {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkInDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkOutDate;
    private Integer roomID;
}
