package com.webapp.booking.controllers;

import com.webapp.booking.services.RoomService;
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

    @GetMapping("/getRoomsWithFilter")
    public String getAllRoomsWithFilter() {
        return "roomsWithFilter";
    }

    @GetMapping("/{roomID}")
    public String getRoomByID(@PathVariable int roomID, Model model) {
        model.addAttribute("roomByID", roomService.getRoomByID(roomID));
        return "roomByID";
    }

    @PostMapping()
    public String createRoom() {
        return null;
    }

    @PutMapping()
    public String updateRoom() {
        return null;
    }

    @DeleteMapping("/{roomID}")
    public String deleteRoom(@PathVariable int roomID) {
        roomService.deleteRoom();
        return null;
    }

    @PutMapping("/bookRoom")
    public String bookRoom() {
        return null;
    }

    @PutMapping("/addDiscount")
    public String addDiscount() {
        return null;
    }

    @DeleteMapping("/removeDiscount/{roomID}")
    public String deleteDiscount(@PathVariable int roomID) {
        roomService.deleteDiscount(roomID);
        return null;
    }

}
