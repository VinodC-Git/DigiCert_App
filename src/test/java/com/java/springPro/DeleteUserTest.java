package com.java.springPro;

import com.java.springPro.entity.User;
import com.java.springPro.repository.UserRepository;
import com.java.springPro.service.UserService;
import com.java.springPro.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeleteUserTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;
    @Test
    public void whenGivenId_shouldDeleteUser_ifFound(){
        User user = new User();
        User.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .email("ramesh@gmail.com")
                .build();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        userService.deleteUserById(user.getId());
        verify(userRepository).deleteById(user.getId());
    }

}