package com.webapp.booking.requests.user;

import com.webapp.booking.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAllUsersWithFilterArguments {
    String login;
    String name;
    UserRole userRole;
}
