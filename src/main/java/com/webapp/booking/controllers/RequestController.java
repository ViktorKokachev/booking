package com.webapp.booking.controllers;


import com.webapp.booking.requests.request.CreateRequestArguments;
import com.webapp.booking.services.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/requests")
@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RequestController {

    private final RequestService requestService;

    @GetMapping
    public String getAllRequests(Model model) {
        model.addAttribute("allRequests", requestService.getAllRequests());
        return "allRequests";
    }

    @GetMapping("/{userID}")
    public String getAllRequestsByUserID(@PathVariable int userID, Model model) {
        model.addAttribute("allRequestsByUserID", requestService.getAllRequestsByUserID(userID));
        return "allRequestsByUserID";
    }

    @GetMapping("/{requestID}")
    public String getRequestByID(@PathVariable int requestID, Model model) {
        model.addAttribute("requestByID", requestService.getRequestByID(requestID));
        return "requestByID";
    }

    // add arguments
    @PostMapping("/create")
    public String createRequest(Model model, @ModelAttribute CreateRequestArguments createRequestArguments) {
        System.out.println(createRequestArguments);
        return "client/discountRooms";
    }

    // add arguments
    @PutMapping
    public String payRequest() {
        return null;
    }

    @DeleteMapping("/{requestID}")
    public String declineRequest(@PathVariable int requestID) {
        requestService.declineRequest(requestID);
        return null;
    }
}
