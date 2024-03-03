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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UsersListTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void shouldReturnAllUsers() {
        List<User> users = new ArrayList();
        users.add(new User());
        given(userRepository.findAll()).willReturn(users);
        List<User> expected = userService.fetchUserList();
        assertEquals(expected, users);
        verify(userRepository).findAll();
    }
}