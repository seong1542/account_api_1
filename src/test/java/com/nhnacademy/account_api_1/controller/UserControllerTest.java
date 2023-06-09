package com.nhnacademy.account_api_1.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.account_api_1.entity.Status;
import com.nhnacademy.account_api_1.request.UserModify;
import com.nhnacademy.account_api_1.request.UserRequest;
import com.nhnacademy.account_api_1.request.UserStatus;
import com.nhnacademy.account_api_1.response.UserLoginResponse;
import com.nhnacademy.account_api_1.response.UserResponse;
import com.nhnacademy.account_api_1.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private final static Long ID = 2L;
    private final static String USER_ID = "test7";
    private final static String EMAIL = "nhn@academy.com";
    @Test
    @DisplayName("Index_Id 값으로 유저 찾기")
    void getUser() throws Exception {
        final UserResponse response = new UserResponse(USER_ID, EMAIL);
        given(userService.getUser(ID)).willReturn(response);
        final String json = new ObjectMapper().writeValueAsString(response);

        mockMvc.perform(get("/users/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(userService).getUser(ID);
    }

    @Test
    @DisplayName("유저 등록")
    void createUser() throws Exception {
        final UserRequest request = new UserRequest(USER_ID, "1234", EMAIL);
        final UserResponse response = new UserResponse(USER_ID, EMAIL);
        final String json = new ObjectMapper().writeValueAsString(request);

        given(userService.insertUser(request)).willReturn(response);

        mockMvc.perform(post("/users", request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("입력값 검증 테스트")
    void whenNullValue_thenReturns400() throws Exception {
        final UserRequest request = new UserRequest(null, null, EMAIL);
        final UserResponse response = new UserResponse(USER_ID, EMAIL);
        final String json = new ObjectMapper().writeValueAsString(response);
        given(userService.insertUser(request)).willReturn(response);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("유저 정보 비밀번호, 이메일 수정")
    void modifyUser() throws Exception {
        final UserModify modify = new UserModify("1234", EMAIL);
        final String json = new ObjectMapper().writeValueAsString(modify);
        mockMvc.perform(put("/users/{indexId}", ID)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("유저 상태 변경")
    void modifyUserStatus() throws Exception {
        final UserStatus status = new UserStatus(Status.StatusName.DORMANT);
        final String json = new ObjectMapper().writeValueAsString(status);

        mockMvc.perform(put("/users/withdrawal/{indexId}", ID)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("유저 삭제")
    void deleteUser() throws Exception {

        mockMvc.perform(delete("/users/withdrawal/{indexId}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("유저 ID, PWD 검색")
    void getUserInfo() throws Exception {
        final UserLoginResponse response = new UserLoginResponse(USER_ID, "1234");
        final String json = new ObjectMapper().writeValueAsString(response);
        given(userService.findUserInfo(USER_ID)).willReturn(response);

        mockMvc.perform(get("/users/login/{userID}", USER_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(USER_ID))
                .andExpect(jsonPath("$.password").value("1234"));

        verify(userService, times(1)).findUserInfo(USER_ID);
    }
}