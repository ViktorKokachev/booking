package com.webapp.booking.controllers;


import com.webapp.booking.services.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/rooms")
@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomController {

    private RoomService roomService;

    @GetMapping()
    public String getAllRooms(Model model) {
        model.addAttribute("allRooms", roomService.getAllRooms());
        return "allRooms";
    }

    @GetMapping("/withFilter")
    public String getAllRoomsWithFilter() {
        return "withFilters";
    }

    @PostMapping("/book")
    public String bookRoom() {
        return null;
    }

    @PutMapping()
    public String createRoom() {
        return null;
    }

    @PostMapping()
    public String updateRoom() {
        return null;
    }

    @DeleteMapping()
    public String deleteRoom() {
        return null;
    }

    @PostMapping("/addDiscount")
    public String addDiscount() {
        return null;
    }

    @DeleteMapping("/removeDiscount/{roomID}")
    public String deleteDiscount(@PathVariable int roomID) {
        roomService.deleteDiscount(roomID);
        return null;
    }

}
