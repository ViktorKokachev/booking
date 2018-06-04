package com.webapp.booking.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello")
@RestController
public class TestController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "Hello, World!";
    }
}
