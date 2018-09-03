package com.webapp.booking.controllers;


import com.webapp.booking.entities.RequestEntity;
import com.webapp.booking.requests.request.CreateRequestArguments;
import com.webapp.booking.requests.request.GetAllRequestsWithFilterArguments;
import com.webapp.booking.requests.request.PayRequestArguments;
import com.webapp.booking.requests.request.UpdateRequestArguments;
import com.webapp.booking.services.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/requests")
@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RequestController {

    private RequestService requestService;

    // todo:  provide authorities
    @GetMapping("/adminRequests")
    public String getAdminRequests(Model model) {
        model.addAttribute("allRequests", requestService.getAllRequests());
        model.addAttribute("getAllRequestsWithFilterArguments", new GetAllRequestsWithFilterArguments());
        return "admin/requestsList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String getAllRequests(Model model) {
        model.addAttribute("allRequests", requestService.getAllRequests());
        return null;
    }

    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("/user/{userID}")
    public String getAllRequestsByUserID(Model model, @PathVariable Integer userID) {
        model.addAttribute("allRequestsByUserID", requestService.getAllRequestsByUserID(userID));
        return "null";
    }

    @PreAuthorize("hasAnyAuthority('CLIENT', 'ADMIN')")
    @GetMapping("/{requestID}")
    public String getRequestByID(Model model, @PathVariable Integer requestID) {
        RequestEntity requestByID = requestService.getRequestByID(requestID);
        model.addAttribute("requestByID", requestByID);
        model.addAttribute("requestSum", requestService.getRequestSum(requestByID));
        return "client/userRequest";
    }

    @PreAuthorize("hasAuthority('CLIENT')")
    @PostMapping("/create")
    public String createRequest(Model model, @ModelAttribute CreateRequestArguments createRequestArguments) {
        requestService.createRequest(createRequestArguments);

        // TODO: add condition which checks for succesful request creation
        // TODO: hardcoded userID

        return "redirect:/users/myAccount";
    }

    @PreAuthorize("hasAuthority('CLIENT')")
    @PostMapping("/update")
    public String updateRequest(Model model, @ModelAttribute UpdateRequestArguments updateRequestArguments) {
        requestService.updateRequest(updateRequestArguments);
        return null;
    }

    @PreAuthorize("hasAuthority('CLIENT')")
    @PostMapping("/pay/{requestID}")
    public String payRequest(Model model, @ModelAttribute PayRequestArguments payRequestArguments,
                             @PathVariable Integer requestID) {
        requestService.payRequest(requestID, payRequestArguments);
        //todo: fix hardcoded number
        return "redirect:/users/myAccount";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/reject/{requestID}")
    public String rejectRequest(Model model, @PathVariable Integer requestID) {
        requestService.rejectRequest(requestID);
        return null;
    }

    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("/pay/{requestID}")
    public String payRequestPage(Model model, @ModelAttribute PayRequestArguments payRequestArguments,
                             @PathVariable Integer requestID) {
        model.addAttribute("requestID", requestID);
        model.addAttribute("payRequestArguments", new PayRequestArguments());
        return "client/payRequest";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/approve/{requestID}")
    public String approveRequest(Model model, @PathVariable Integer requestID) {
        requestService.approveRequest(requestID);
        return null;
    }

    @PreAuthorize("hasAuthority('CLIENT')")
    @DeleteMapping("/{requestID}")
    public String deleteRequest(Model model, @PathVariable Integer requestID) {
        requestService.deleteRequest(requestID);
        return null;
    }
}
