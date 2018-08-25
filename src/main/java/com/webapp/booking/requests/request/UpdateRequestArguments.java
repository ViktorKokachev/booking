package com.webapp.booking.requests.request;

import com.webapp.booking.enums.RequestStatus;

import java.time.LocalDateTime;

public class UpdateRequestArguments {

    private Integer requestID;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private RequestStatus requestStatus;
}
