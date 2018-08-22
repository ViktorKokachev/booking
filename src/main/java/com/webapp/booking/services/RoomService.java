package com.webapp.booking.services;

import com.webapp.booking.repos.RoomRepo;
import com.webapp.booking.entities.RoomEntity;
import com.webapp.booking.requests.room.AddDiscountArguments;
import com.webapp.booking.requests.room.CreateRoomArguments;
import com.webapp.booking.requests.room.GetAllRoomsWithFilterArguments;
import com.webapp.booking.requests.room.UpdateRoomArguments;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomService {

    @Autowired
    private RoomRepo roomRepo;

    public List<RoomEntity> getAllRoomsWithFilter(GetAllRoomsWithFilterArguments getAllRoomsWithFilterArguments) {
            return roomRepo.getAllRooms();
            //return roomRepo.getAllRoomsWithFilters(getAllRoomsWithFilterArguments);
        }

    /*public void bookRoom() {

    }*/

    public RoomEntity getRoomByID(int roomID) {
        return null;
    }

    public void createRoom(CreateRoomArguments createRoomArguments) {

    }

    public void updateRoom(UpdateRoomArguments updateRoomArguments) {

    }

    public void deleteRoom(int roomID) {

    }

    public void addDiscount(AddDiscountArguments addDiscountArguments) {

    }

    public void deleteDiscount(int roomID) {

    }

    public List<RoomEntity> getDiscountRooms() {
        //todo: fix magic number
        int amount = 5;
        return roomRepo.getDiscountRooms(amount);
    }
}
