package com.webapp.booking.entities;

import com.webapp.booking.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntity {
    private Integer userID;
    private String login;
    private String password;
    private String name;
    private UserRole userRole;
}
