package com.webapp.booking.requests.request;

import com.webapp.booking.enums.RequestStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAllRequestsWithFilterArguments {
    private String name;
    private RequestStatus requestStatus;
}
