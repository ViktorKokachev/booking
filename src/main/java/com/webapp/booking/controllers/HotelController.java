package com.webapp.booking.controllers;

import com.webapp.booking.requests.hotel.CreateHotelArguments;
import com.webapp.booking.requests.hotel.UpdateHotelArguments;
import com.webapp.booking.services.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/hotels")
@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HotelController {

    private final HotelService hotelService;

    @GetMapping()
    public String getAllHotels(Model model) {
        model.addAttribute("allHotels", hotelService.getAllHotels());
        return "allHotels";
    }

    @GetMapping("/{ownerID}")
    public String getAllHotelsByOwnerID(@PathVariable Integer ownerID, Model model) {
        model.addAttribute("allHotelsByOwnerID", hotelService.getAllHotelsByOwnerID(ownerID));
        return "hotelByOwnerID";
    }


    @GetMapping("/{hotelID}")
    public String getHotelByID(@PathVariable Integer hotelID, Model model) {
        model.addAttribute("hotelByID", hotelService.getHotelByID(hotelID));
        return "hotelByID";
    }

    @PostMapping("/create")
    public String createHotel(CreateHotelArguments createHotelArguments, Model model) {
        hotelService.createHotel(createHotelArguments);
        return null;
    }

    //add arguments
    @PostMapping("/update")
    public String updateHotel(UpdateHotelArguments updateHotelArguments, Model model) {
        hotelService.updateHotel(updateHotelArguments);
        return null;
    }

    @DeleteMapping("/{hotelID}")
    public String deleteHotel(@PathVariable int hotelID, Model model) {
        hotelService.deleteHotel(hotelID);
        return null;
    }
}
