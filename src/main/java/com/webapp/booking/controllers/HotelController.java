package com.webapp.booking.controllers;

import com.webapp.booking.enums.UserRole;
import com.webapp.booking.requests.hotel.CreateHotelArguments;
import com.webapp.booking.requests.hotel.GetAllHotelsWithFilterArguments;
import com.webapp.booking.requests.hotel.UpdateHotelArguments;
import com.webapp.booking.requests.room.CreateRoomArguments;
import com.webapp.booking.requests.room.UpdateRoomArguments;
import com.webapp.booking.services.HotelService;
import com.webapp.booking.services.RoomService;
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
    private final RoomService roomService;

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

        createHotelArguments.setOwnerID(userService.getCurrentUser().getUserID());

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

        return "redirect:/hotels/" + hotelID;
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

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @GetMapping("/{hotelID}/rooms")
    public String getHotelRooms(@PathVariable Integer hotelID, Model model) {
        model.addAttribute("hotelID", hotelID);
        model.addAttribute("allRoomsByHotelID", roomService.getRoomsByHotelID(hotelID));
        if (userService.getUserRoleByLogin() == UserRole.ADMIN) {
            return "admin/adminRoomsList";
        } else {
            return "owner/ownerRoomsList";
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @GetMapping("/{hotelID}/rooms/{roomID}")
    public String getRoomFromHotelRooms(@PathVariable Integer hotelID, @PathVariable Integer roomID,
                                        Model model) {
        model.addAttribute("roomByID", roomService.getRoomByID(roomID));
        if (userService.getUserRoleByLogin() == UserRole.ADMIN) {
            return "admin/adminRoomByID";
        } else {
            return "owner/ownerRoomByID";
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @GetMapping("/{hotelID}/rooms/create")
    public String createRoomInHotelPage(@PathVariable Integer hotelID,
                                        Model model) {
        model.addAttribute("createRoomArguments", new CreateRoomArguments());
        if (userService.getUserRoleByLogin() == UserRole.ADMIN) {
            return "admin/createRoom";
        } else {
            return "owner/createRoom";
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @PostMapping("/{hotelID}/rooms/create")
    public String createRoomInHotel(@PathVariable Integer hotelID,
                                    CreateRoomArguments createRoomArguments, Model model) {

        createRoomArguments.setHotelID(hotelID);
        roomService.createRoom(createRoomArguments);

        return "redirect:/hotels/" + hotelID + "/rooms";
        /*if (userService.getUserRoleByLogin() == UserRole.ADMIN) {
            return "admin/adminRoomByID";
        } else {
            return "owner/ownerRoomByID";
        }*/
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @GetMapping("/{hotelID}/rooms/{roomID}/update")
    public String updateRoomFromHotelRoomsPage(@PathVariable Integer hotelID, @PathVariable Integer roomID,
                                        Model model) {
        model.addAttribute("roomByID", new UpdateRoomArguments());
        if (userService.getUserRoleByLogin() == UserRole.ADMIN) {
            return "admin/updateRoom";
        } else {
            return "owner/updateRoom";
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @PostMapping("/{hotelID}/rooms/{roomID}/update")
    public String updateRoomFromHotelRooms(@PathVariable Integer hotelID, @PathVariable Integer roomID,
                                           UpdateRoomArguments updateRoomArguments, Model model) {

        updateRoomArguments.setRoomID(roomID);
        roomService.updateRoom(updateRoomArguments);

        return "redirect:/hotels/" + hotelID + "/rooms/" + roomID;
        /*if (userService.getUserRoleByLogin() == UserRole.ADMIN) {
            return "admin/adminRoomByID";
        } else {
            return "owner/ownerRoomByID";
        }*/
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @PostMapping("/{hotelID}/rooms/{roomID}/delete")
    public String deleteRoomFromHotelRooms(@PathVariable Integer hotelID, @PathVariable Integer roomID,
                                        Model model) {

        roomService.deleteRoom(roomID);

        return "redirect:/hotels/" + hotelID + "/rooms";

        /*if (userService.getUserRoleByLogin() == UserRole.ADMIN) {
            return "admin/adminRoomByID";
        } else {
            return "owner/ownerRoomByID";
        }*/
    }



}
