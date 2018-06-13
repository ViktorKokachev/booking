package com.webapp.booking.controllers;

import com.webapp.booking.common.Paths;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(Paths.USERS)
@RestController
public class UserController {

    @GetMapping()
    public String getAllUsers() {
        return null;
    }

    @PutMapping()
    public String createUser() {
        return null;
    }

    @DeleteMapping
    public String deleteUser() {
        return null;
    }
}
