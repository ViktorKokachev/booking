package com.webapp.booking.controllers;

import com.webapp.booking.common.Paths;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(Paths.REQUESTS)
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RequestController {

    @GetMapping()
    public String getAllRequests() {
        return null;
    }

    @GetMapping("/getByID")
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

    @DeleteMapping()
    public String declineRequest() {
        return null;
    }
}
