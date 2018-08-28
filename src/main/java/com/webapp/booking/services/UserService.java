package com.webapp.booking.services;

import com.webapp.booking.entities.UserEntity;
import com.webapp.booking.repos.UserRepo;
import com.webapp.booking.requests.user.CreateUserArguments;
import com.webapp.booking.requests.user.UpdateUserArguments;
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

    public void updateUser(UpdateUserArguments updateUserArguments) {
        UserEntity userEntity = getUserByID(updateUserArguments.getUserID());

        mergeWhenUpdate(userEntity, updateUserArguments);

        userRepo.updateUser(userEntity);
    }

    private void mergeWhenUpdate(UserEntity toUpdate, UpdateUserArguments updateUserArguments) {
        if (updateUserArguments.getLogin() != null) {
            toUpdate.setLogin(updateUserArguments.getLogin());
        }
        if (updateUserArguments.getName() != null) {
            toUpdate.setName(updateUserArguments.getName());
        }
        if (updateUserArguments.getPassword() != null && !updateUserArguments.getPassword().isEmpty()) {
            toUpdate.setPassword(updateUserArguments.getPassword());
        }
        if (updateUserArguments.getUserRole() != null) {
            toUpdate.setUserRole(updateUserArguments.getUserRole());
        }

    }

    public void createUser(CreateUserArguments createUserArguments) {

        UserEntity userEntity = new UserEntity();

        userEntity.setLogin(createUserArguments.getLogin());
        userEntity.setName(createUserArguments.getName());
        userEntity.setPassword(createUserArguments.getPassword());
        userEntity.setUserRole(createUserArguments.getUserRole());

        userRepo.createUser(userEntity);
    }

    public void deleteUser(Integer userID) {
        userRepo.deleteUser(userID);
    }
}
