package com.webapp.booking.controllers;

import com.webapp.booking.common.Paths;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(Paths.ROOMS)
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomController {

    @GetMapping()
    public String getAllRooms(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
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

    @DeleteMapping("/removeDiscount")
    public String deleteDiscount() {
        return null;
    }

}
