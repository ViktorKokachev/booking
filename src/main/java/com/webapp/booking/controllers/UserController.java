package com.webapp.booking.controllers;

import com.webapp.booking.enums.UserRole;
import com.webapp.booking.requests.user.CreateUserArguments;
import com.webapp.booking.requests.user.GetAllUsersWithFilterArguments;
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
        model.addAttribute("getAllUsersWithFilterArguments", new GetAllUsersWithFilterArguments());
        return "admin/usersList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/withFilter")
    public String getAllUsersWithFilter(Model model,
                                        @ModelAttribute GetAllUsersWithFilterArguments getAllUsersWithFilterArguments) {
        model.addAttribute("allUsers", userService.getAllUsersWithFilter(getAllUsersWithFilterArguments));
        return "admin/usersList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{userID}")
    public String getUserByID(@PathVariable int userID, Model model) {
        model.addAttribute("userByID", userService.getUserByID(userID));
        model.addAttribute("updateUserArguments", new UpdateUserArguments());
        return "admin/adminUser";
    }

    @PreAuthorize("hasAnyAuthority('CLIENT', 'ADMIN', 'OWNER')")
    @GetMapping("/myAccount")
    public String getMyAccount(Model model) {

        UserRole userRole = userService.getUserRoleByLogin();

        if (userRole == UserRole.CLIENT) {
            model.addAttribute("userInformation",
                    userService.getUserByID(userService.getCurrentUser().getUserID()));
            model.addAttribute("allRequestsByUser",
                    requestService.getAllRequestsByUserID(userService.getCurrentUser().getUserID()));
            model.addAttribute("updateUserArguments", new UpdateUserArguments());

            return "client/clientAccount";
        } else if (userRole == UserRole.ADMIN) {
            model.addAttribute("userByID",
                    userService.getUserByID(userService.getCurrentUser().getUserID()));
            model.addAttribute("updateUserArguments", new UpdateUserArguments());

            return "admin/adminAccount";
        } else {
            model.addAttribute("userByID",
                    userService.getUserByID(userService.getCurrentUser().getUserID()));
            model.addAttribute("updateUserArguments", new UpdateUserArguments());

            return "admin/adminAccount";
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/create")
    public String createUserPage(Model model, @ModelAttribute CreateUserArguments createUserArguments) {
        model.addAttribute("createUserArguments", new CreateUserArguments());
        return "admin/adminCreateUser";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public String createUser(Model model, @ModelAttribute CreateUserArguments createUserArguments) {
        userService.createUser(createUserArguments);
        return "redirect:/users";
    }

    @PreAuthorize("hasAnyAuthority('CLIENT', 'ADMIN', 'OWNER')")
    @PostMapping("/{userID}/update")
    public String updateUser(Model model, UpdateUserArguments updateUserArguments,
                             @PathVariable Integer userID) {

        /*if (userService.getUserRoleByLogin() == UserRole.CLIENT) {
            updateUserArguments.setUserID(userService.getCurrentUser().getUserID());
        }

        userService.updateUser(updateUserArguments);

        if (userService.getUserRoleByLogin() == UserRole.CLIENT) {

            model.addAttribute("userInformation",
                    userService.getUserByID(userService.getCurrentUser().getUserID()));
            model.addAttribute("allRequestsByUser",
                    requestService.getAllRequestsByUserID(userService.getCurrentUser().getUserID()));
            model.addAttribute("updateUserArguments", new UpdateUserArguments());

            return "redirect:/users/myAccount";

        }
*/
        if (userService.getUserRoleByLogin() == UserRole.ADMIN) {
            updateUserArguments.setUserID(userID);
            userService.updateUser(updateUserArguments);
        }
        return "redirect:/users/" + updateUserArguments.getUserID();

    }

    @PreAuthorize("hasAnyAuthority('CLIENT', 'ADMIN', 'OWNER')")
    @PostMapping("/myAccount/update")
    public String updateCurrentUser(Model model, UpdateUserArguments updateUserArguments) {

        Integer userID = userService.getCurrentUser().getUserID();
        updateUserArguments.setUserID(userID);

        userService.updateUser(updateUserArguments);

        if (userService.getUserRoleByLogin() == UserRole.CLIENT) {
            model.addAttribute("userInformation",
                    userService.getUserByID(userID));
            model.addAttribute("allRequestsByUser",
                    requestService.getAllRequestsByUserID(userID));
        }

        model.addAttribute("userByID",
                userService.getUserByID(userService.getCurrentUser().getUserID()));
        model.addAttribute("updateUserArguments", new UpdateUserArguments());

        return "redirect:/users/myAccount";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{userID}")
    public String deleteUser(@PathVariable int userID, Model model) {
        userService.deleteUser(userID);
        return "redirect:/users";
    }
}
