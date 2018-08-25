package com.webapp.booking.requests.request;

import com.webapp.booking.enums.RequestStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UpdateRequestArguments {

    private Integer requestID;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkInDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkOutDate;
    private RequestStatus requestStatus;
}
