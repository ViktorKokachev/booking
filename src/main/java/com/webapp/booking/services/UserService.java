package com.webapp.booking.services;

import com.webapp.booking.entities.UserEntity;
import com.webapp.booking.repos.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<UserEntity> getAllUsers() {
        return userRepo.getAllUsers();
    }

    public UserEntity getUserByID(Integer userID) {
        List<UserEntity> userByID = userRepo.getUserByID(userID);
        if (userByID.size() == 0) {
            throw new RuntimeException("There is no user with this ID");
        } else if (userByID.size() > 1) {
            throw new RuntimeException("There are more than one user with this ID");
        } else {
            return userByID.get(0);
        }
    }

    public void updateUser() {

    }

    public void createUser() {
    }

    public void deleteUser(int userID) {

    }
}
