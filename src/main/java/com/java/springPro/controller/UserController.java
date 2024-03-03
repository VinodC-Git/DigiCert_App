package com.java.springPro.controller;


import com.java.springPro.entity.User;
import com.java.springPro.exception.UserAlreadyExistedException;
import com.java.springPro.repository.UserRepository;
import com.java.springPro.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    private UserService userService;

    private UserRepository userRepository;

    @GetMapping("/getUserList")
    public List<User> fetchUserList() {
        return userService.fetchUserList();
        // return userRepository.findAll();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUsers(@PathVariable("userId") long userId) {
        return userService.getUsersById(userId).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/saveUsers")
    public User saveUser(@Valid @RequestBody User user) throws UserAlreadyExistedException {
        return userService.saveUser(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long userId, @RequestBody User user) {
        return userService.getUsersById(userId)
                .map(savedUser -> {

                    savedUser.setFirstName(user.getFirstName());
                    savedUser.setLastName(user.getLastName());
                    savedUser.setEmail(user.getEmail());

                    User updatedUser = userService.updateUser(savedUser);
                    return new ResponseEntity<>(updatedUser, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long userId){

        userService.deleteUserById(userId);

        return new ResponseEntity<String>("User deleted successfully!.", HttpStatus.OK);

    }


}
