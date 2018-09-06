package com.webapp.booking.services;

import com.webapp.booking.entities.HotelEntity;
import com.webapp.booking.entities.UserEntity;
import com.webapp.booking.repos.HotelRepo;
import com.webapp.booking.repos.RoomRepo;
import com.webapp.booking.requests.hotel.CreateHotelArguments;
import com.webapp.booking.requests.hotel.GetAllHotelsWithFilterArguments;
import com.webapp.booking.requests.hotel.UpdateHotelArguments;
import com.webapp.booking.requests.room.UpdateRoomArguments;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HotelService {

    private UserService userService;

    private HotelRepo hotelRepo;

    public List<HotelEntity> getAllHotels() {
        return hotelRepo.getAllHotels();
    }

    public List<HotelEntity> getAllHotelsByOwnerID(Integer ownerID) {
        return hotelRepo.getAllHotelsByOwnerID(ownerID);
    }

    public HotelEntity getHotelByID(Integer hotelID) {
        List<HotelEntity> hotelEntities = hotelRepo.getHotelByID(hotelID);

        if (hotelEntities.size() == 0) {
            throw new RuntimeException();
        } else if (hotelEntities.size() > 1) {
            throw new RuntimeException();
        } else return hotelEntities.get(0);
    }

    public void createHotel(CreateHotelArguments createHotelArguments) {

        HotelEntity hotelEntity = new HotelEntity();

        hotelEntity.setRating(createHotelArguments.getRating());
        hotelEntity.setOwnerID(createHotelArguments.getOwnerID());
        hotelEntity.setName(createHotelArguments.getName());
        hotelEntity.setIsApproved(createHotelArguments.getIsApproved());
        hotelEntity.setDescription(createHotelArguments.getDescription());
        hotelEntity.setAddress(createHotelArguments.getAddress());

        hotelRepo.createHotel(hotelEntity);
    }

    public void updateHotel(UpdateHotelArguments updateHotelArguments) {

        HotelEntity hotelEntity = getHotelByID(updateHotelArguments.getHotelID());

        mergeWhenUpdate(hotelEntity, updateHotelArguments);

        hotelRepo.updateHotel(hotelEntity);
    }

    public void deleteHotel(Integer hotelID) {
        hotelRepo.deleteHotel(hotelID);
    }

    private void mergeWhenUpdate(HotelEntity toUpdate, UpdateHotelArguments updateHotelArguments) {
        if (updateHotelArguments.getAddress() != null) {
            toUpdate.setAddress(updateHotelArguments.getAddress());
        }
        if (updateHotelArguments.getDescription() != null) {
            toUpdate.setDescription(updateHotelArguments.getDescription());
        }
        if (updateHotelArguments.getIsApproved() != null) {
            toUpdate.setIsApproved(updateHotelArguments.getIsApproved());
        }
        if (updateHotelArguments.getName() != null) {
            toUpdate.setName(updateHotelArguments.getName());
        }
        if (updateHotelArguments.getOwnerID() != null) {
            toUpdate.setOwnerID(updateHotelArguments.getOwnerID());
        }
        if (updateHotelArguments.getRating() != null) {
            toUpdate.setRating(updateHotelArguments.getRating());
        }
    }

    public List<HotelEntity> getAllHotelsWithFilter(GetAllHotelsWithFilterArguments getAllHotelsWithFilterArguments) {
        // todo: return real filtered hotels
        return getAllHotels();
    }

    public List<HotelEntity> getAllHotelsWithFilterForOwner(GetAllHotelsWithFilterArguments getAllHotelsWithFilterArguments) {
        UserEntity currentUser = userService.getCurrentUser();
        return getAllHotels();
    }
}
