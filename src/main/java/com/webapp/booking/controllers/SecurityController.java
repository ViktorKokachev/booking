package com.webapp.booking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class SecurityController {
    @GetMapping("/login")
    public String getLoginPage() {
        return "security/login";
    }

    @PostMapping("/login")
    public String signIn(String password, String username) {

        if (!password.equals("123")) {
            return "redirect:/security/loginError";
        }

        return "redirect:/rooms";
    }

    @GetMapping("/loginError")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "security/error";
    }
}
