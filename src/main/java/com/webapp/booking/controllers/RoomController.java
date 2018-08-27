package com.webapp.booking.controllers;


import com.webapp.booking.entities.RoomEntity;
import com.webapp.booking.requests.other.ExtendedRequestArguments;
import com.webapp.booking.requests.request.CreateRequestArguments;
import com.webapp.booking.requests.room.*;
import com.webapp.booking.services.HotelService;
import com.webapp.booking.services.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/rooms")
@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private HotelService hotelService;

    @GetMapping()
    public String getDiscountRooms(Model model) {
        model.addAttribute("discountRooms", roomService.getDiscountRooms());
        model.addAttribute("getAllRoomsWithFilterArguments", new GetAllRoomsWithFilterArguments());
        model.addAttribute("extendedRequestArguments", new ExtendedRequestArguments());
        return "client/discountRooms";
    }

    @PostMapping("/withFilter")
    public String getAllRoomsWithFilter(@ModelAttribute GetAllRoomsWithFilterArguments getAllRoomsWithFilterArguments,
                                        Model model) {
            model.addAttribute("allRoomsWithFilter", roomService.getAllRoomsWithFilter(getAllRoomsWithFilterArguments));
        return "client/allRoomsWithFilter";
    }

    @PostMapping("/book/{roomID}")
    public String getBookingRoom(@PathVariable Integer roomID, Model model) {
        model.addAttribute("bookingRoom", roomService.getRoomByID(roomID));
        model.addAttribute("createRequestArguments", new CreateRequestArguments());
        return "client/bookRoom";
    }

    @PostMapping("/create")
    public String createRoom(@RequestBody CreateRoomArguments createRoomArguments, Model model) {
        roomService.createRoom(createRoomArguments);
        return null;
    }

    @PostMapping("/update/{roomID}")
    public String updateRoom(@RequestBody UpdateRoomArguments updateRoomArguments, @PathVariable Integer roomID,
                             Model model) {
        roomService.updateRoom(updateRoomArguments);
        return null;
    }

    @DeleteMapping("/{roomID}")
    public String deleteRoom(@PathVariable int roomID, Model model) {
        roomService.deleteRoom(roomID);
        return null;
    }

    @PostMapping("/addDiscount")
    public String addDiscount(@ModelAttribute AddDiscountArguments addDiscountArguments, Model model) {
        roomService.addDiscount(addDiscountArguments);
        return null;
    }

    @DeleteMapping("/removeDiscount/{roomID}")
    public String deleteDiscount(@PathVariable int roomID, Model model) {
        roomService.deleteDiscount(roomID);
        return null;
    }
}
