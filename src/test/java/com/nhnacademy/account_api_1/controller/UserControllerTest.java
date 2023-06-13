package com.nhnacademy.account_api_1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.account_api_1.entity.Status;
import com.nhnacademy.account_api_1.request.UserModify;
import com.nhnacademy.account_api_1.request.UserRequest;
import com.nhnacademy.account_api_1.request.UserStatus;
import com.nhnacademy.account_api_1.response.UserResponse;
import com.nhnacademy.account_api_1.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
    private final static String PASSWORD = "1234";
    private final static String EMAIL = "nhn@academy.com";
    @Test
    @DisplayName("Index_Id 값으로 유저 찾기")
    void getUser() throws Exception {
        final UserResponse response = new UserResponse(USER_ID, PASSWORD, Status.StatusName.JOINED,EMAIL);
        given(userService.getUser(USER_ID)).willReturn(Optional.of(response));
        final String json = new ObjectMapper().writeValueAsString(response);

        mockMvc.perform(get("/users/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(userService).getUser(USER_ID);
    }

    //TODO 1. TEST 다시 작성. void로 반환되도록
//    @Test
//    @DisplayName("유저 등록")
//    void createUser() throws Exception {
//        final UserRequest request = new UserRequest(USER_ID, PASSWORD, EMAIL);
//        final Map<String, String> response = Map.of("result", "회원가입되었습니다. 로그인해주세요.");
//        final String json = new ObjectMapper().writeValueAsString(request);
//
//        given(userService.insertUser(request)).willReturn(response);
//
//        mockMvc.perform(post("/users", request)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isCreated());
//    }

//    @Test
//    @DisplayName("입력값 검증 테스트")
//    void whenNullValue_thenReturns400()
//            throws Exception {
//        final UserRequest request = new UserRequest(null, null, EMAIL);
//        final Map<String, String> response = Map.of("result", "회원가입되었습니다. 로그인해주세요.");
//        final String json = new ObjectMapper().writeValueAsString(response);
//        given(userService.insertUser(request)).willReturn(response);
//
//        mockMvc.perform(post("/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isBadRequest());
//    }

    @Test
    @DisplayName("유저 정보 비밀번호, 이메일 수정")
    void modifyUser() throws Exception {
        final UserModify modify = new UserModify(PASSWORD, EMAIL);
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

}