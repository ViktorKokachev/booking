package com.webapp.booking.requests.other;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SignUpArguments {
    String login;
    String name;
    String password;
    String repeatedPassword;
}
