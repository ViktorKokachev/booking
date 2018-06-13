package com.webapp.booking.services;

import com.webapp.booking.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    public List<UserEntity> getAllUsers() {
        return null;
    }

    public void createUser() {
    }

    public void deleteUser() {

    }
}
