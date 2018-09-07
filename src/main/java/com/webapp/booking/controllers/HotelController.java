package com.webapp.booking.controllers;

import com.webapp.booking.enums.UserRole;
import com.webapp.booking.requests.hotel.CreateHotelArguments;
import com.webapp.booking.requests.hotel.GetAllHotelsWithFilterArguments;
import com.webapp.booking.requests.hotel.UpdateHotelArguments;
import com.webapp.booking.services.HotelService;
import com.webapp.booking.services.UserService;
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
    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @GetMapping()
    public String getAllHotels(Model model) {
        if (userService.getUserRoleByLogin() == UserRole.ADMIN) {
            model.addAttribute("getAllHotelsWithFilterArguments", new GetAllHotelsWithFilterArguments());
            model.addAttribute("allHotels", hotelService.getAllHotels());
            return "admin/hotelsList";
        } else {
            model.addAttribute("allHotels", hotelService.getAllHotelsForCurrentOwner());
            return "owner/ownerHotelsList";
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public String getAllHotelsWithFilter(Model model,
                                         @ModelAttribute GetAllHotelsWithFilterArguments getAllHotelsWithFilterArguments) {
        model.addAttribute("allHotels", hotelService.getAllHotelsWithFilter(getAllHotelsWithFilterArguments));

        return "admin/hotelsList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/owner/{ownerID}")
    public String getAllHotelsByOwnerID(@PathVariable Integer ownerID, Model model) {
        model.addAttribute("allHotelsByOwnerID", hotelService.getAllHotelsByOwnerID(ownerID));
        if (userService.getUserRoleByLogin() == UserRole.ADMIN) {
            return "owner/ownerHotelsList";
        } else {
            return "owner/ownerHotelsList";
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @GetMapping("/{hotelID}")
    public String getHotelByID(@PathVariable Integer hotelID, Model model) {
        model.addAttribute("updateHotelArguments", new UpdateHotelArguments());
        model.addAttribute("hotelByID", hotelService.getHotelByID(hotelID));
        if (userService.getUserRoleByLogin() == UserRole.ADMIN) {
            return "admin/adminHotel";
        } else {
            return "owner/ownerHotel";
        }
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @GetMapping("/create")
    public String createHotelPage(Model model) {
        model.addAttribute("createHotelArguments", new CreateHotelArguments());
        return "/owner/createHotel";
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @PostMapping("/create")
    public String createHotel(CreateHotelArguments createHotelArguments, Model model) {
        hotelService.createHotel(createHotelArguments);
        return "redirect:/hotels";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @GetMapping("{hotelID}/update")
    public String updateHotelPage
            (UpdateHotelArguments updateHotelArguments, Model model, @PathVariable Integer hotelID) {

        model.addAttribute("updateHotelArguments", new UpdateHotelArguments());

        if (userService.getUserRoleByLogin() == UserRole.ADMIN) {
            return "admin/updateHotel";
        } else {
            return "owner/updateHotel";
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @PostMapping("{hotelID}/update")
    public String updateHotel
    (UpdateHotelArguments updateHotelArguments, Model model, @PathVariable Integer hotelID) {

        updateHotelArguments.setHotelID(hotelID);

        hotelService.updateHotel(updateHotelArguments);

        model.addAttribute("hotelByID", hotelService.getHotelByID(updateHotelArguments.getHotelID()));

        return "redirect:/hotels" + hotelID;
        /*if (userService.getUserRoleByLogin() == UserRole.ADMIN) {
            return "admin/adminHotel";
        } else {
            return "owner/ownerHotel";
        }*/
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @PostMapping("/{hotelID}/delete")
    public String deleteHotel(@PathVariable int hotelID, Model model) {
        hotelService.deleteHotel(hotelID);
        return "redirect:/hotels";
    }
}
