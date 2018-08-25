package com.webapp.booking.entities;

import com.webapp.booking.enums.RequestStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class RequestEntity {
    private Integer requestID;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkInDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkOutDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date requestDate;
    private RequestStatus requestStatus;
    private Integer roomID;
    private Integer userID;
}
