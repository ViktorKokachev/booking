package com.webapp.booking.services;

import com.webapp.booking.entities.UserEntity;
import com.webapp.booking.enums.UserRole;
import com.webapp.booking.repos.UserRepo;
import com.webapp.booking.requests.other.SignUpArguments;
import com.webapp.booking.requests.user.CreateUserArguments;
import com.webapp.booking.requests.user.GetAllUsersWithFilterArguments;
import com.webapp.booking.requests.user.UpdateUserArguments;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService implements UserDetailsService {

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
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            toUpdate.setPassword(bCryptPasswordEncoder.encode(updateUserArguments.getPassword()));
        }
        if (updateUserArguments.getUserRole() != null) {
            toUpdate.setUserRole(updateUserArguments.getUserRole());
        }

    }

    public void createUser(CreateUserArguments createUserArguments) {

        UserEntity userEntity = new UserEntity();

        userEntity.setLogin(createUserArguments.getLogin());
        userEntity.setName(createUserArguments.getName());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userEntity.setPassword(bCryptPasswordEncoder.encode(createUserArguments.getPassword()));
        userEntity.setUserRole(createUserArguments.getUserRole());

        userRepo.createUser(userEntity);
    }

    public void deleteUser(Integer userID) {
        userRepo.deleteUser(userID);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserEntity> userByID = userRepo.getUserByLogin(username);
        UserEntity userEntity;

        if (userByID.size() == 0) {
            throw new UsernameNotFoundException("There is no user with this login");
        } else if (userByID.size() > 1) {
            throw new UsernameNotFoundException("There are more than one user with this login");
        } else {
            userEntity = userByID.get(0);
        }

        final List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(userEntity.getUserRole().toString()));

        return new User(userEntity.getLogin(), userEntity.getPassword(), grantedAuthorityList);
    }

    public void signUp(SignUpArguments signUpArguments) {

        UserEntity userEntity = new UserEntity();

        if (userRepo.getUserByLogin(signUpArguments.getLogin()) != null) {
            throw new RuntimeException("We already have user with this login!");
        }

        userEntity.setLogin(signUpArguments.getLogin());

        userEntity.setName(signUpArguments.getName());

        if (!signUpArguments.getPassword().equals(signUpArguments.getRepeatedPassword()))
            throw new RuntimeException("Passwords are not the same!");

        userEntity.setPassword(signUpArguments.getPassword());
        userEntity.setUserRole(UserRole.CLIENT);

        userRepo.createUser(userEntity);
    }

    public UserEntity getCurrentUser() {

        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // todo: add some checks
        UserEntity userByLogin = userRepo.getUserByLogin(authUser.getUsername()).get(0);

        return userByLogin;
    }

    public Boolean isCurrentUserAuthenticated() {
        return  SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                //when Anonymous Authentication is enabled
                !(SecurityContextHolder.getContext().getAuthentication()
                        instanceof AnonymousAuthenticationToken);
    }

    public UserRole getUserRoleByLogin() {

        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // todo: add some checks
        UserEntity userByLogin = userRepo.getUserByLogin(authUser.getUsername()).get(0);

        return userByLogin.getUserRole();
    }

    public List<UserEntity> getAllUsersWithFilter(GetAllUsersWithFilterArguments getAllUsersWithFilterArguments) {

        return userRepo.getAllUsersWithFilter(getAllUsersWithFilterArguments.getLogin(),
                getAllUsersWithFilterArguments.getName(),
                getAllUsersWithFilterArguments.getUserRole());
    }
}
