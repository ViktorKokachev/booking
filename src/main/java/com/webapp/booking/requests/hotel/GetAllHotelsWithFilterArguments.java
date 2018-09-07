package com.webapp.booking.requests.hotel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAllHotelsWithFilterArguments {
    String name;
    Integer rating;
}
