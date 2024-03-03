package com.java.springPro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.springPro.controller.UserController;
import com.java.springPro.entity.User;
import com.java.springPro.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@EnableWebMvc
public class UserControllerITests {


    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

   /* @Mock
    UserController userController;*/

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
    }

    @Test
    public void givenUserObject_whenCreateUser_thenReturnSavedUser() throws Exception {

        // given - precondition or setup
        User user = User.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .email("ramesh@gmail.com")
                .build();

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/api/saveUsers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",
                        is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName",
                        is(user.getLastName())))
                .andExpect(jsonPath("$.email",
                        is(user.getEmail())));

    }

    // JUnit test for Get All users REST API
    @Test
    public void givenListOfUsers_whenGetAllUsers_thenReturnUsersList() throws Exception {
        // given - precondition or setup
        List<User> listOfUsers = new ArrayList<>();
        listOfUsers.add(User.builder().firstName("Ramesh").lastName("Fadatare").email("ramesh@gmail.com").build());
        listOfUsers.add(User.builder().firstName("Tony").lastName("Stark").email("tony@gmail.com").build());
        userRepository.saveAll(listOfUsers);
        System.out.println("size>>>"+listOfUsers);
        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/getUserList"));
        System.out.println("size>>>"+listOfUsers.size());

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfUsers.size())));

    }

    // positive scenario - valid user id
    // JUnit test for GET user by id REST API
    @Test
    public void givenUserId_whenGetUserById_thenReturnUserObject() throws Exception {
        // given - precondition or setup
        User user = User.builder()
                .id(1)
                .firstName("Ramesh")
                .lastName("Fadat")
                .email("ramesh@gmail.com")
                .build();
        userRepository.save(user);

        ResultActions response = mockMvc.perform(get("/api/user/{userId}", user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));
        System.out.println(user.getId());

        // then - verify the output
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.email", is(user.getEmail())));

    }

    // negative scenario - valid user id
    // JUnit test for GET user by id REST API
    @Test
    public void givenInvalidUserId_whenGetUserById_thenReturnEmpty() throws Exception {
        long userId = 111L;
        User user = User.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .email("ramesh@gmail.com")
                .build();
        userRepository.save(user);
        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/user/{userId}", userId));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());

    }

    // JUnit test for update user REST API - positive scenario
    @Test
    public void givenUpdatedUser_whenUpdateUser_thenReturnUpdateUserObject() throws Exception {
        User savedUser = User.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .email("ramesh@gmail.com")
                .build();
      //  given(userRepository.findById(savedUser.getId())).willReturn(Optional.empty());
        userRepository.save(savedUser);

        User updatedUser = User.builder()
                .firstName("Ram")
                .lastName("Jadhav")
                .email("ramam@gmail.com")
                .build();

        // given(userRepository.findById(anyLong())).willReturn(Optional.empty());
        ResultActions response = mockMvc.perform(put("/api/users/{id}",savedUser.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUser)));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(updatedUser.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(updatedUser.getLastName())))
                .andExpect(jsonPath("$.email", is(updatedUser.getEmail())));
    }

    // JUnit test for update user REST API - negative scenario
    @Test
    public void givenUpdatedUser_whenUpdateUser_thenReturn404() throws Exception {
        // given - precondition or setup
        long userId = 111L;

        User savedUser = User.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .email("ramesh@gmail.com")
                .build();
        userRepository.save(savedUser);

        User updateUser = User.builder()
                .firstName("Ram")
                .lastName("Jadhav")
                .email("ram@gmail.com")
                .build();

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/api/users/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateUser)));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    // JUnit test for delete user REST API
    @Test
    public void givenUserId_whenDeleteUser_thenReturn200() throws Exception {
        // given - precondition or setup
      //  userId
        User savedUser = User.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .email("ramesh@gmail.com")
                .build();
        userRepository.save(savedUser);
        System.out.println("savedUser"+savedUser.getId());

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/api/users/{id}", savedUser.getId()));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}

