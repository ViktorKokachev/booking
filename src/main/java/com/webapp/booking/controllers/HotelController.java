package com.webapp.booking.controllers;

import com.webapp.booking.services.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String getAllHotelsByOwnerID(@RequestParam int ownerID, Model model) {
        model.addAttribute("allHotelsByOwnerID", hotelService.getAllHotelsByOwnerID(ownerID));
        return "hotelByOwnerID";
    }


    @GetMapping("/{hotelID}")
    public String getHotelByID(@RequestParam int hotelID, Model model) {
        model.addAttribute("hotelByID", hotelService.getHotelByID(hotelID));
        return "hotelByID";
    }

    // add arguments
    @PostMapping()
    public String createHotel() {
        return null;
    }

    //add arguments
    @PutMapping()
    public String updateHotel() {
        return null;
    }

    @DeleteMapping("/{hotelID}")
    public String deleteHotel(@PathVariable int hotelID, Model model) {
        hotelService.deleteHotel(hotelID);
        return null;
    }
}
