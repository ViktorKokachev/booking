package com.webapp.booking.services;

import com.webapp.booking.entities.RoomEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomService {

    public List<RoomEntity> getAllRoomsWithFilter() {
        return null;
    }

    public List<RoomEntity> getAllRooms() {
        return null;
    }

    public void bookRoom() {

    }

    public RoomEntity getRoomByID(int roomID) {
        return null;
    }

    public void createRoom() {

    }

    public void editRoom() {

    }

    public void deleteRoom() {

    }

    public void addDiscount() {

    }

    public void deleteDiscount(int roomID) {

    }
}
