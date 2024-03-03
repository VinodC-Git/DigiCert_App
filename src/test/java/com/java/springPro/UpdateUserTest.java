package com.java.springPro;

import com.java.springPro.entity.User;
import com.java.springPro.repository.UserRepository;
import com.java.springPro.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UpdateUserTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl updateUserService;

    @Test
    public void whenGivenId_shouldUpdateUser_ifFound() {
        User user = new User();
        user.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .email("ramesh@gmail.com")
                .build();
        User newUser = new User();
        newUser.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .email("ramesh@gmail.com")
                .build();
        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
        updateUserService.updateUser(newUser);
        verify(userRepository).save(newUser);
    }

}