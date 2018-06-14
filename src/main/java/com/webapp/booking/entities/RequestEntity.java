package com.webapp.booking.entities;

import com.webapp.booking.enums.RequestStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RequestEntity {
    private Integer requestID;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private RequestStatus requestStatus;
    private Integer roomID;
    private LocalDateTime requestDate;
}
