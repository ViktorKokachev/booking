package com.webapp.booking.requests.other;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginArguments {
    String password;
    String username;
}
