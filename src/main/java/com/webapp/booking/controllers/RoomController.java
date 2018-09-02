package com.webapp.booking.controllers;

import com.webapp.booking.requests.other.ExtendedRequestArguments;
import com.webapp.booking.requests.request.CreateRequestArguments;
import com.webapp.booking.requests.room.*;
import com.webapp.booking.services.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/rooms")
@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomController {

    private RoomService roomService;

    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping()
    public String getDiscountRooms(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        System.err.println(currentPrincipalName);
        System.err.println(authentication.getAuthorities());


        model.addAttribute("discountRooms", roomService.getDiscountRooms());
        model.addAttribute("getAllRoomsWithFilterArguments", new GetAllRoomsWithFilterArguments());
        model.addAttribute("extendedRequestArguments", new ExtendedRequestArguments());
        return "client/discountRooms";
    }

    @PreAuthorize("hasAuthority('CLIENT')")
    @PostMapping("/withFilter")
    public String getAllRoomsWithFilter(@ModelAttribute GetAllRoomsWithFilterArguments getAllRoomsWithFilterArguments,
                                        Model model) {
            model.addAttribute("allRoomsWithFilter", roomService.getAllRoomsWithFilter(getAllRoomsWithFilterArguments));
        return "client/allRoomsWithFilter";
    }

    @PreAuthorize("hasAuthority('CLIENT')")
    @PostMapping("/book/{roomID}")
    public String getBookingRoom(@PathVariable Integer roomID, Model model) {
        model.addAttribute("bookingRoom", roomService.getRoomByID(roomID));
        model.addAttribute("createRequestArguments", new CreateRequestArguments());
        return "client/bookRoom";
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @PostMapping("/create")
    public String createRoom(@RequestBody CreateRoomArguments createRoomArguments, Model model) {
        roomService.createRoom(createRoomArguments);
        return null;
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @PostMapping("/update/{roomID}")
    public String updateRoom(@RequestBody UpdateRoomArguments updateRoomArguments, @PathVariable Integer roomID,
                             Model model) {
        roomService.updateRoom(updateRoomArguments);
        return null;
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @DeleteMapping("/{roomID}")
    public String deleteRoom(@PathVariable int roomID, Model model) {
        roomService.deleteRoom(roomID);
        return null;
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @PostMapping("/addDiscount")
    public String addDiscount(@ModelAttribute AddDiscountArguments addDiscountArguments, Model model) {
        roomService.addDiscount(addDiscountArguments);
        return null;
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @DeleteMapping("/removeDiscount/{roomID}")
    public String deleteDiscount(@PathVariable Integer roomID, Model model) {
        roomService.deleteDiscount(roomID);
        return null;
    }
}
