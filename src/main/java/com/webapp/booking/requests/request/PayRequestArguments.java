package com.webapp.booking.requests.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayRequestArguments {

     String cardNumber;
     String cardholderName;
     String cardExpireDate;
     String cardCVV;
}
