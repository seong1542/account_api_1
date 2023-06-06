package com.nhnacademy.account_api_1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.account_api_1.request.UserRequest;
import com.nhnacademy.account_api_1.response.UserResponse;
import com.nhnacademy.account_api_1.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getUsers() throws Exception {
        List<UserResponse> list = List.of(
                new UserResponse("test1", "test1@email.com"),
                new UserResponse("test2", "test2@email.com"));

        String jsonBody = new ObjectMapper().writeValueAsString(list);

        given(userService.getUsers()).willReturn(list);

        this.mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk());

        verify(userService, times(1)).getUsers();
    }

    @Test
    void getUser() throws Exception {
        final UserResponse userResponse = new UserResponse("test1", "test1#email.com");
        final String jsonBody = new ObjectMapper().writeValueAsString(userResponse);

        given(userService.getUser(1L)).willReturn(userResponse);

        this.mockMvc.perform(get("/users/{indexId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk());

        verify(userService).getUser(1L);
    }

    @Test
    @DisplayName("회원가입")
    void createUser() throws Exception {
        final UserResponse userResponse = new UserResponse("testId", "test@nhnacademy.com");
        final UserRequest userRequest = new UserRequest("testId","1234","test@nhnacademy.com");

        String jsonBody = new ObjectMapper().writeValueAsString(userResponse);

        given(userService.insertUser(userRequest)).willReturn(userResponse);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated());

    }

    @Test
    void modifyUser() {

    }

    @Test
    void modifyUserStatus() {

    }

    @Test
    void deleteUser() {

    }
}