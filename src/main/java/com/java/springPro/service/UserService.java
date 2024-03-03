package com.java.springPro.service;

import com.java.springPro.entity.User;
import com.java.springPro.exception.UserAlreadyExistedException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {


    List<User> fetchUserList();

    Optional<User> getUsersById(long id);

    User saveUser(User user) throws UserAlreadyExistedException;

    User updateUser(User saveUser);

    void deleteUserById(long id);


}
