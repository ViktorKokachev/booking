package com.webapp.booking.controllers;

import com.webapp.booking.common.Paths;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(Paths.REQUESTS)
@RestController
public class RequestController {

    @GetMapping()
    public String getAllRequests() {
        return null;
    }

    @GetMapping()
    public String getAllRequestsByID() {
        return null;
    }

    @PutMapping
    public String createRequest() {
        return null;
    }

    @PostMapping()
    public String payRequest() {
        return null;
    }

    @PostMapping()
    public String declineRequest() {
        return null;
    }
}
