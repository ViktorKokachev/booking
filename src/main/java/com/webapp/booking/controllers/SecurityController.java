package com.webapp.booking.controllers;

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

    UserService userService;

    @PreAuthorize("permitAll()")
    @GetMapping({"/", "/index"})
    public String getIndexPage(Model model) {
        model.addAttribute("loginArguments", new LoginArguments());
        return "index";
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public String signIn(LoginArguments loginArguments) {


        System.err.println(loginArguments.toString());

        // fix

        if (!loginArguments.getPassword().equals("123")) {
            return "redirect:/loginError";
        }

        return "redirect:/rooms";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/signUp")
    public String getSignUpPage(Model model) {
        model.addAttribute("signUpArguments", new SignUpArguments());
        return "security/signUp";
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/signUp")
    public String signUp(Model model, SignUpArguments signUpArguments) {

        return "redirect:/index";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/signOut")
    public String signOut() {
        return null;
    }

}
