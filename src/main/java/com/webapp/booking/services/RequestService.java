package com.webapp.booking.services;

import com.webapp.booking.entities.RequestEntity;
import com.webapp.booking.entities.RoomEntity;
import com.webapp.booking.entities.UserEntity;
import com.webapp.booking.enums.RequestStatus;
import com.webapp.booking.repos.RequestRepo;
import com.webapp.booking.requests.request.CreateRequestArguments;
import com.webapp.booking.requests.request.PayRequestArguments;
import com.webapp.booking.requests.request.UpdateRequestArguments;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RequestService {

    @Autowired
    RequestRepo requestRepo;

    private static final String CARD_NUMBER_PATTERN = "^[0-9]{16}$";
    private static final String CARD_CVV_PATTERN = "^[0-9]{3}$";
    private static final String CARDHOLDER_NAME_PATTERN = "^[a-zA-Z]+[ ][a-zA-Z]+$";
    private static final String CARD_EXPIRATION_DATE_PATTERN = "^(1[0-2]|0[1-9])\\/([0-9]{2}$)";

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
        // check dates for availability, return price by whole period
        RequestEntity requestEntity = new RequestEntity();

        Integer userID = 1;

        //TODO: fix hardcoded userID
        requestEntity.setUserEntity(new UserEntity());
        requestEntity.getUserEntity().setUserID(userID);
        requestEntity.setRequestStatus(RequestStatus.BOOKED);
        requestEntity.setRoom(new RoomEntity());
        requestEntity.getRoom().setRoomID(createRequestArguments.getRoomID());
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

    public void payRequest(Integer requestID, PayRequestArguments payRequestArguments) {

        if (isCreditCardValid(payRequestArguments.getCardNumber(), payRequestArguments.getCardHolderName(),
                payRequestArguments.getCardExpirationDate(), payRequestArguments.getCardCVV())) {
            requestRepo.payRequest(requestID);
        }
        else {
            throw new RuntimeException("Payment failed");
        }
    }

    public void rejectRequest(Integer requestID) {
        requestRepo.rejectRequest(requestID);
    }

    private Boolean isCreditCardValid(String cardNumber, String cardHolderName, String cardExpirationDate, String cardCVV) {
        return isCardNumberValid(cardNumber) && isCardHolderNameValid(cardHolderName)
                && isCardExpirationDateValid(cardExpirationDate) && isCardCVVValid(cardCVV);
    }

    private static Boolean isCardNumberValid(String cardNumber)
    {

        if (!cardNumber.matches(CARD_NUMBER_PATTERN))
            return false;

        // Luhn algorithm

        int nDigits = cardNumber.length();

        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--)
        {
            int d = cardNumber.charAt(i) - 'a';
            if (isSecond)
                d = d * 2;
            // We add two digits to handle
            // cases that make two digits
            // after doubling
            nSum += d / 10;
            nSum += d % 10;
            isSecond = !isSecond;
        }
        return (nSum % 10 == 0);
    }

    private static Boolean isCardHolderNameValid(String cardHolderName) {
        return cardHolderName.matches(CARDHOLDER_NAME_PATTERN);
    }

    private static Boolean isCardExpirationDateValid(String cardExpirationDate) {
        return cardExpirationDate.matches(CARD_EXPIRATION_DATE_PATTERN);
    }

    private static Boolean isCardCVVValid(String cardCVV) {
        return cardCVV.matches(CARD_CVV_PATTERN);
    }
}
