package com.webapp.booking.requests.user;

import com.webapp.booking.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserArguments {
    private Integer userID;
    private String login;
    private String password;
    private String oldPassword;
    private String name;
    private UserRole userRole;
}
