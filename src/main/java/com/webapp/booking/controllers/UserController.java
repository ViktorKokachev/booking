package com.webapp.booking.controllers;

import com.webapp.booking.requests.user.CreateUserArguments;
import com.webapp.booking.requests.user.UpdateUserArguments;
import com.webapp.booking.services.RequestService;
import com.webapp.booking.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private UserService userService;
    private RequestService requestService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "admin/allUsers";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{userID}")
    public String getUserByID(@PathVariable int userID, Model model) {
        model.addAttribute("userByID", userService.getUserByID(userID));
        return "admin/userByID";
    }

    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("/myAccount")
    public String getMyAccount(Model model) {

        // todo: fix hardcoded userID
        Integer userID = 1;

        model.addAttribute("userInformation", userService.getUserByID(userID));
        model.addAttribute("allRequestsByUser", requestService.getAllRequestsByUserID(userID));
        model.addAttribute("updateUserArguments", new UpdateUserArguments());
        return "client/clientAccount";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public String createUser(Model model, @ModelAttribute CreateUserArguments createUserArguments) {
        userService.createUser(createUserArguments);
        return null;
    }

    @PreAuthorize("hasAnyAuthority('CLIENT', 'ADMIN', 'OWNER')")
    @PostMapping("/update")
    public String updateUser(Model model, UpdateUserArguments updateUserArguments) {

        // todo: fix hardcoded userID
        Integer userID = 1;
        updateUserArguments.setUserID(userID);

        userService.updateUser(updateUserArguments);

        model.addAttribute("userInformation", userService.getUserByID(userID));
        model.addAttribute("allRequestsByUser", requestService.getAllRequestsByUserID(userID));
        model.addAttribute("updateUserArguments", new UpdateUserArguments());
        // todo: only client case, make implementation for admin
        return "redirect:/users/myAccount";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{userID}")
    public String deleteUser(@PathVariable int userID, Model model) {
        userService.deleteUser(userID);
        return null;
    }
}
