package com.webapp.booking.requests.room;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddDiscountArguments {

    private Double discount;
    private Integer roomID;
}
