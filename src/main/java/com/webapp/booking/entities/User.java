package com.webapp.booking.entities;

import com.webapp.booking.enums.UserRole;

public class User extends AbstractEntity{
    private String login;
    private String password;
    private String name;
    private UserRole userRole;
}
