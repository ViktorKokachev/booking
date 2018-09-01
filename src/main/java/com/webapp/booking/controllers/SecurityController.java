package com.webapp.booking.controllers;

import com.webapp.booking.requests.other.LoginArguments;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class SecurityController {

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


    /*@GetMapping("/loginError")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "security/error";
    }*/

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "security/signUp";
    }

    @PostMapping("/signUp")
    public String signUp() {
        return null;
    }


}
