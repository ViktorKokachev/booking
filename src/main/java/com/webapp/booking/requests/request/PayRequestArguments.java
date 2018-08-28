package com.webapp.booking.requests.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayRequestArguments {

     String cardNumber;
     String cardHolderName;
     String cardExpirationDate;
     String cardCVV;
}
