package com.webapp.booking.controllers;

import com.webapp.booking.entities.UserEntity;
import com.webapp.booking.enums.UserRole;
import com.webapp.booking.requests.other.LoginArguments;
import com.webapp.booking.requests.other.SignUpArguments;
import com.webapp.booking.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityController {

    private UserService userService;

    @PreAuthorize("permitAll()")
    @GetMapping({"/", "/index"})
    public String getIndexPage(Model model) {

        UserEntity currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            model.addAttribute("loginArguments", new LoginArguments());
            return "index";
        }

        if (currentUser.getUserRole() == UserRole.CLIENT) {
            return "redirect:/rooms";
        }

        if (currentUser.getUserRole() == UserRole.ADMIN) {
            return "redirect:/requests";
        }

        if (currentUser.getUserRole() == UserRole.OWNER) {
            return "redirect:/hotels";
        }

        throw new RuntimeException("Something went wrong with authorization");

    }

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public String signIn(LoginArguments loginArguments) {

        return "redirect:/rooms";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/signUp")
    public String getSignUpPage(Model model) {

        UserEntity currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            model.addAttribute("signUpArguments", new SignUpArguments());
            return "security/signUp";
        }

        if (currentUser.getUserRole() == UserRole.CLIENT) {
            return "redirect:/rooms";
        }

        if (currentUser.getUserRole() == UserRole.ADMIN) {
            return "redirect:/requests";
        }

        if (currentUser.getUserRole() == UserRole.OWNER) {
            return "redirect:/hotels";
        }

        throw new RuntimeException("Something went wrong with authorization");
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/signUp")
    public String signUp(Model model, SignUpArguments signUpArguments) {
        userService.signUp(signUpArguments);
        return "redirect:/index";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/signOut")
    public String signOut() {
        return "index";
    }

}
