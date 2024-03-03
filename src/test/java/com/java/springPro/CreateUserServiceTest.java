package com.java.springPro;

import com.java.springPro.entity.User;
import com.java.springPro.exception.UserAlreadyExistedException;
import com.java.springPro.repository.UserRepository;
import com.java.springPro.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateUserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;
    @Test
    public void whenSaveUser_shouldReturnUser() throws UserAlreadyExistedException {
        User user = new User();
        User.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .email("ramesh@gmail.com")
                .build();
        //user.setFirstName("Ramesh");
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        User createUser = userService.saveUser(user);
        assertThat(createUser.getFirstName()).isSameAs(user.getFirstName());
        verify(userRepository).save(user);
    }
}