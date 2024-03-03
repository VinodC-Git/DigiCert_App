package com.java.springPro.service;

import com.java.springPro.entity.User;
import com.java.springPro.exception.UserAlreadyExistedException;
import com.java.springPro.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;

    @Override
    public User saveUser(User user) throws UserAlreadyExistedException {

        User savedUser = userRepository.findByEmail(user.getEmail());
        if(savedUser != null ){
            throw new UserAlreadyExistedException("Customer already exists!!");
        } else
        return userRepository.save(user);
    }

    @Override
    public List<User> fetchUserList() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Optional<User> getUsersById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(User updatedUser) {
        return userRepository.save(updatedUser);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }


}
