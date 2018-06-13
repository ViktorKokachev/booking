package com.webapp.booking.controllers;

import com.webapp.booking.common.Paths;
import com.webapp.booking.entities.HotelEntity;
import com.webapp.booking.services.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(Paths.HOTELS)
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HotelController {

    private final HotelService hotelService;

    @GetMapping()
    public String getAllHotels(Model model) {
        model.addAttribute("allHotels", hotelService.getAllHotels());
        return "allHotels";
    }

    @PutMapping()
    public String createHotel() {
        return null;
    }

    @PostMapping()
    public String updateHotel() {
        return null;
    }

    @DeleteMapping()
    public String deleteHotel() {
        return null;
    }


}
