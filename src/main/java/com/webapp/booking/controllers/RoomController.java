package com.webapp.booking.controllers;


import com.webapp.booking.requests.room.AddDiscountArguments;
import com.webapp.booking.requests.room.CreateRoomArguments;
import com.webapp.booking.requests.room.GetAllRoomsWithFilterArguments;
import com.webapp.booking.requests.room.UpdateRoomArguments;
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
        model.addAttribute("getAllRoomsWithFilterArguments", new GetAllRoomsWithFilterArguments());
        return "allRooms";
    }

    @PostMapping("/withFilter")
    public String getAllRoomsWithFilter(
            @ModelAttribute GetAllRoomsWithFilterArguments getAllRoomsWithFilterArguments,
            Model model) {
        model.addAttribute("allRoomsWithFilter",
                roomService.getAllRoomsWithFilter(getAllRoomsWithFilterArguments));
        return "allRoomsWithFilter";
    }

    @PostMapping()
    public String createRoom(@RequestBody CreateRoomArguments createRoomArguments) {
        roomService.createRoom(createRoomArguments);
        return null;
    }

    @PutMapping()
    public String updateRoom(@RequestBody UpdateRoomArguments updateRoomArguments) {
        roomService.updateRoom(updateRoomArguments);
        return null;
    }

    @DeleteMapping("/{roomID}")
    public String deleteRoom(@PathVariable int roomID) {
        roomService.deleteRoom(roomID);
        return null;
    }

    @PutMapping("/addDiscount")
    public String addDiscount(@RequestBody AddDiscountArguments addDiscountArguments) {
        roomService.addDiscount(addDiscountArguments);
        return null;
    }

    @DeleteMapping("/removeDiscount/{roomID}")
    public String deleteDiscount(@PathVariable int roomID) {
        roomService.deleteDiscount(roomID);
        return null;
    }

}
