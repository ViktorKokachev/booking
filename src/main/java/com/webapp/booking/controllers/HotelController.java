package com.webapp.booking.controllers;

import com.webapp.booking.common.Paths;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(Paths.HOTELS)
@RestController
public class HotelController {

    @GetMapping()
    public String getAllHotels() {
        return null;
    }

    @PutMapping()
    public String createHotel() {
        return null;
    }

    @PostMapping()
    public String editHotel() {
        return null;
    }

    @DeleteMapping()
    public String deleteHotel() {
        return null;
    }


}
