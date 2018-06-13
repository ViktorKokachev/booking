package com.webapp.booking.services;

import com.webapp.booking.entities.RequestEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RequestService {
    public List<RequestEntity> getAllRequests() {
        return null;
    }

    public List<RequestEntity> getAllRequestsByID() {
        return null;
    }

    public void createRequest() {

    }

    public void payRequest() {

    }

    public void declineRequest() {

    }
}
