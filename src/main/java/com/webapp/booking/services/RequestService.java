package com.webapp.booking.services;

import com.webapp.booking.entities.RequestEntity;
import com.webapp.booking.repos.RequestRepo;
import com.webapp.booking.requests.request.CreateRequestArguments;
import com.webapp.booking.requests.request.UpdateRequestArguments;
import com.webapp.booking.requests.user.UpdateUserArguments;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RequestService {

    @Autowired
    RequestRepo requestRepo;

    public List<RequestEntity> getAllRequests() {
        return requestRepo.getAllRequests();
    }

    public List<RequestEntity> getAllRequestsByUserID(Integer userID) {
        return requestRepo.getAllRequestsByUserID(userID);
    }

    public RequestEntity getRequestByID(Integer requestID) {
        List<RequestEntity> requestByID = requestRepo.getRequestByID(requestID);

        if (requestByID.size() == 0) {
            throw new RuntimeException("There is no request with such ID");
        } else if (requestByID.size() > 2) {
            throw new RuntimeException("There are more than one request with such ID");
        } else {
            return requestByID.get(0);
        }
    }

    public void createRequest(CreateRequestArguments createRequestArguments) {


        // check dates for availability, return price by


        RequestEntity requestEntity = new RequestEntity();

        //TODO: fix hardcoded userID
        requestEntity.setUserID(1);
        requestEntity.setRoomID(createRequestArguments.getRoomID());
        requestEntity.setCheckInDate(createRequestArguments.getCheckInDate());
        requestEntity.setCheckOutDate(createRequestArguments.getCheckOutDate());

        requestRepo.createRequest(requestEntity);
    }

    public void updateRequest(UpdateRequestArguments updateRequestArguments) {

        RequestEntity requestByID = getRequestByID(updateRequestArguments.getRequestID());

        mergeWhenUpdate(requestByID, updateRequestArguments);

        requestRepo.updateRequest(requestByID);

    }

    private void mergeWhenUpdate(RequestEntity toUpdate, UpdateRequestArguments updateRequestArguments) {
        if (updateRequestArguments.getCheckInDate() != null) {
            toUpdate.setCheckInDate(updateRequestArguments.getCheckInDate());
        }
        if (updateRequestArguments.getCheckOutDate() != null) {
            toUpdate.setCheckOutDate(updateRequestArguments.getCheckOutDate());
        }
        if (updateRequestArguments.getRequestStatus() != null) {
            toUpdate.setRequestStatus(updateRequestArguments.getRequestStatus());
        }

    }

    public void deleteRequest(Integer requestID) {
        requestRepo.deleteRequest(requestID);
    }

    public void payRequest() {

    }

    public void declineRequest(int requestID) {

    }
}
