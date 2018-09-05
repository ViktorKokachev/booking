package com.webapp.booking.controllers;

import com.webapp.booking.requests.hotel.CreateHotelArguments;
import com.webapp.booking.requests.hotel.GetAllHotelsWithFilterArguments;
import com.webapp.booking.requests.hotel.UpdateHotelArguments;
import com.webapp.booking.services.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/hotels")
@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HotelController {

    private final HotelService hotelService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public String getAllHotels(Model model) {
        model.addAttribute("allHotels", hotelService.getAllHotels());
        model.addAttribute("getAllHotelsWithFilterArguments", new GetAllHotelsWithFilterArguments());
        return "admin/hotelsList";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @GetMapping("/owner/{ownerID}")
    public String getAllHotelsByOwnerID(@PathVariable Integer ownerID, Model model) {
        model.addAttribute("allHotelsByOwnerID", hotelService.getAllHotelsByOwnerID(ownerID));
        return "hotelByOwnerID";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @GetMapping("/{hotelID}")
    public String getHotelByID(@PathVariable Integer hotelID, Model model) {
        model.addAttribute("updateHotelArguments", new UpdateHotelArguments());
        model.addAttribute("hotelByID", hotelService.getHotelByID(hotelID));
        return "admin/adminHotel";
    }

    @PreAuthorize("hasAuthority('OWNER')")
    public String createHotel(CreateHotelArguments createHotelArguments, Model model) {
        hotelService.createHotel(createHotelArguments);
        return null;
    }

    //add arguments
    @PreAuthorize("hasAuthority('OWNER')")
    @PostMapping("/update")
    public String updateHotel(UpdateHotelArguments updateHotelArguments, Model model) {
        hotelService.updateHotel(updateHotelArguments);
        return null;
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @DeleteMapping("/{hotelID}")
    public String deleteHotel(@PathVariable int hotelID, Model model) {
        hotelService.deleteHotel(hotelID);
        return null;
    }
}
