package com.webapp.booking.controllers;


import com.webapp.booking.requests.request.CreateRequestArguments;
import com.webapp.booking.requests.request.PayRequestArguments;
import com.webapp.booking.services.RequestService;
import com.webapp.booking.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/requests")
@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping
    public String getAllRequests(Model model) {
        model.addAttribute("allRequests", requestService.getAllRequests());
        return "allRequests";
    }

    @GetMapping("/user/{userID}")
    public String getAllRequestsByUserID(Model model, @PathVariable Integer userID) {
        model.addAttribute("allRequestsByUserID", requestService.getAllRequestsByUserID(userID));
        return "null";
    }

    @GetMapping("/{requestID}")
    public String getRequestByID(Model model, @PathVariable Integer requestID) {
        model.addAttribute("requestByID", requestService.getRequestByID(requestID));
        return null;
    }

    @PostMapping("/create")
    public String createRequest(Model model, @ModelAttribute CreateRequestArguments createRequestArguments) {
        requestService.createRequest(createRequestArguments);

        // TODO: add condition which checks for succesful request creation
        // TODO: hardcoded userID

        return "redirect:/users/myAccount";
    }

    @PostMapping("/update/{requestID}")
    public String updateRequest(Model model) {
        return null;
    }

    @PostMapping("/pay/{requestID}")
    public String payRequest(Model model, @ModelAttribute PayRequestArguments payRequestArguments,
                             @PathVariable Integer requestID) {
        requestService.payRequest(requestID, payRequestArguments);
        return null;
    }

    @PostMapping("/reject/{requestID}")
    public String rejectRequest(Model model, @PathVariable Integer requestID) {
        requestService.rejectRequest(requestID);
        return null;
    }

    @PostMapping("/approve/{requestID}")
    public String approveRequest(Model model, @PathVariable Integer requestID) {
        requestService.approveRequest(requestID);
        return null;
    }

    @DeleteMapping("/{requestID}")
    public String deleteRequest(Model model, @PathVariable Integer requestID) {
        requestService.deleteRequest(requestID);
        return null;
    }
}
