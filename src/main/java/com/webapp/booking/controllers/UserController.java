package com.webapp.booking.controllers;

import com.webapp.booking.requests.user.UpdateUserArguments;
import com.webapp.booking.services.RequestService;
import com.webapp.booking.services.UserService;
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

@RequestMapping("/users")
@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "allUsers";
    }

    @GetMapping("/{userID}")
    public String getUserByID(@PathVariable int userID, Model model) {
        model.addAttribute("userByID", userService.getUserByID(userID));
        return "userByID";
    }

    @GetMapping("/myAccount")
    public String getMyAccount(Model model) {

        // todo: fix hardcoded userID
        Integer userID = 1;

        model.addAttribute("userInformation", userService.getUserByID(userID));
        model.addAttribute("allRequestsByUser", requestService.getAllRequestsByUserID(userID));
        model.addAttribute("updateUserArguments", new UpdateUserArguments());
        return "client/clientAccount";
    }

    @PostMapping("/create")
    public String createUser() {
        return null;
    }

    @PostMapping("/update")
    public String updateUser(Model model, UpdateUserArguments updateUserArguments) {

        // todo: fix hardcoded userID
        Integer userID = 1;
        updateUserArguments.setUserID(userID);

        userService.updateUser(updateUserArguments);

        model.addAttribute("userInformation", userService.getUserByID(userID));
        model.addAttribute("allRequestsByUser", requestService.getAllRequestsByUserID(userID));
        model.addAttribute("updateUserArguments", new UpdateUserArguments());
        return "client/clientAccount";
    }

    @DeleteMapping("/{userID}")
    public String deleteUser(@PathVariable int userID, Model model) {
        userService.deleteUser(userID);
        return null;
    }
}
