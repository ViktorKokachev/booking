package com.webapp.booking.services;

import com.webapp.booking.entities.HotelEntity;
import com.webapp.booking.enums.RoomType;
import com.webapp.booking.repos.RoomRepo;
import com.webapp.booking.entities.RoomEntity;
import com.webapp.booking.requests.room.AddDiscountArguments;
import com.webapp.booking.requests.room.CreateRoomArguments;
import com.webapp.booking.requests.room.GetAllRoomsWithFilterArguments;
import com.webapp.booking.requests.room.UpdateRoomArguments;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomService {

    private RoomRepo roomRepo;

    public List<RoomEntity> getAllRoomsWithFilter(GetAllRoomsWithFilterArguments getAllRoomsWithFilterArguments) {
        Date checkInDate = getAllRoomsWithFilterArguments.getCheckInDate();
        Date checkOutDate = getAllRoomsWithFilterArguments.getCheckOutDate();
        Integer guestAmount = getAllRoomsWithFilterArguments.getGuestAmount();
        Integer hotelRating = getAllRoomsWithFilterArguments.getHotelRating();
        Double minPrice = getAllRoomsWithFilterArguments.getMinPrice();
        Double maxPrice = getAllRoomsWithFilterArguments.getMaxPrice();
        RoomType roomType = getAllRoomsWithFilterArguments.getRoomType();

        if (checkInDate != null && checkOutDate != null) {
            if (checkInDate.compareTo(checkOutDate) >= 0) {
                throw new RuntimeException("Invalid date range!");
            }

            Date currentDate = new Date();
            if (checkInDate.compareTo(currentDate) <=0 || checkOutDate.compareTo(currentDate) <=0) {
                throw new RuntimeException("You can't book on past dates");
            }

            return roomRepo.getAllRoomsWithFilters(checkInDate, checkOutDate, guestAmount, hotelRating, minPrice, maxPrice, roomType);
        } else if (checkInDate == null && checkOutDate == null) {
            return roomRepo.getAllRoomsWithFiltersWithoutDates(guestAmount, hotelRating, minPrice, maxPrice, roomType);
        } else {
            throw new RuntimeException("You should enter both 'Check in' and 'Check out' dates");
        }
    }

    public void bookRoom() {

    }

    public RoomEntity getRoomByID(Integer roomID) {
        List<RoomEntity> roomByID = roomRepo.getRoomByID(roomID);

        if (roomByID.size() == 0) {
            throw new RuntimeException("Room has not been found by ID = " + roomID);
        } else if (roomByID.size() > 1) {
            throw new RuntimeException("There are more than one room with ID = " + roomID);
        } else {
            return roomByID.get(0);
        }
    }

    public void createRoom(CreateRoomArguments createRoomArguments) {

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setHotel(new HotelEntity());


        roomEntity.setRoomType(createRoomArguments.getRoomType());
        roomEntity.setDescription(createRoomArguments.getDescription());
        roomEntity.setDiscount(createRoomArguments.getDiscount());
        roomEntity.setNumber(createRoomArguments.getNumber());
        roomEntity.setGuestAmount(createRoomArguments.getGuestAmount());
        roomEntity.getHotel().setHotelID(createRoomArguments.getHotelID());
        roomEntity.setPrice(createRoomArguments.getPrice());

        roomRepo.createRoom(roomEntity);
    }

    public void updateRoom(UpdateRoomArguments updateRoomArguments) {
        RoomEntity roomEntity = getRoomByID(updateRoomArguments.getRoomID());

        mergeWhenUpdate(roomEntity, updateRoomArguments);

        roomRepo.updateRoom(roomEntity);
    }

    public void deleteRoom(Integer roomID) {
        roomRepo.deleteRoom(roomID);
    }

    public void addDiscount(AddDiscountArguments addDiscountArguments) {
        roomRepo.addDiscount(addDiscountArguments.getRoomID(), addDiscountArguments.getDiscount());
    }

    public void deleteDiscount(Integer roomID) {
        roomRepo.deleteDiscount(roomID);
    }

    public List<RoomEntity> getDiscountRooms() {
        //todo: fix magic number
        int amount = 20;
        return roomRepo.getDiscountRooms(amount);
    }

    public List<RoomEntity> getRoomsByHotelID(Integer hotelID) {
        return roomRepo.getRoomsByHotelID(hotelID);
    }

    private void mergeWhenUpdate(RoomEntity toUpdate, UpdateRoomArguments updateRoomArguments) {
        if (updateRoomArguments.getDescription() != null) {
            toUpdate.setDescription(updateRoomArguments.getDescription());
        }
        if (updateRoomArguments.getGuestAmount() != null) {
            toUpdate.setGuestAmount(updateRoomArguments.getGuestAmount());
        }
        if (updateRoomArguments.getHotelID() != null) {
            toUpdate.getHotel().setHotelID(updateRoomArguments.getHotelID());
        }
        if (updateRoomArguments.getNumber() != null) {
            toUpdate.setNumber(updateRoomArguments.getNumber());
        }
        if (updateRoomArguments.getPrice() != null) {
            toUpdate.setPrice(updateRoomArguments.getPrice());
        }
        if (updateRoomArguments.getRoomType() != null) {
            toUpdate.setRoomType(updateRoomArguments.getRoomType());
        }
    }
}
