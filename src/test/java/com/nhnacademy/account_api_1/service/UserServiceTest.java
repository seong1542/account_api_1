package com.nhnacademy.account_api_1.service;

import com.nhnacademy.account_api_1.entity.Status;
import com.nhnacademy.account_api_1.entity.User;
import com.nhnacademy.account_api_1.repository.StatusRepository;
import com.nhnacademy.account_api_1.repository.UserRepository;
import com.nhnacademy.account_api_1.request.UserModify;
import com.nhnacademy.account_api_1.request.UserRequest;
import com.nhnacademy.account_api_1.request.UserStatus;
import com.nhnacademy.account_api_1.response.UserLoginResponse;
import com.nhnacademy.account_api_1.response.UserResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StatusRepository statusRepository;

    private static final Long ID = 18L;
    private static final String USER_ID = "test_user";
    private static final String EMAIL = "nhn@academy.com";
    private static final String PASSWORD = "1234";

    @Test
    @DisplayName("유저 목록 가져오기")
    void getUsers() {
        final List<UserResponse> userList = userService.getUsers();
        final List<UserResponse> actualList = userRepository.getUserResponseList();

        assertThat(actualList).hasSize(userList.size());
    }

    @Test
    @DisplayName("ID 값으로 유저 찾기")
    void getUser() {
        final UserResponse response = userService.getUser(ID);
        final User actual = userRepository.findById(ID).orElseThrow();

        assertThat(actual.getUserId()).isEqualTo(response.getUserId());
        assertThat(actual.getEmail()).isEqualTo(response.getEmail());
    }

    @Test
    @DisplayName("유저 등록")
    void insertUser() {
        final UserRequest request = new UserRequest(USER_ID, PASSWORD, EMAIL);
        final UserResponse insertUser = userService.insertUser(request);
        final User actual = userRepository.findByUserId(USER_ID).orElseThrow();

        assertThat(actual).isNotNull();
        assertThat(actual.getUserId()).isEqualTo(insertUser.getUserId());
        assertThat(actual.getEmail()).isEqualTo(insertUser.getEmail());
    }

    @Test
    void updateUser() {
        final User beforeUser = userRepository.findById(ID).orElseThrow();
        final UserModify modify = new UserModify("7777", "nhn@naver.com");
        userService.updateUser(ID, modify);
        final User actual = userRepository.findById(ID).orElseThrow();

        assertThat(actual).isNotIn(beforeUser);
        assertThat(actual.getPassword()).isEqualTo(modify.getPassword());
    }


    @Test
    void removeUser() {
        userService.removeUser(ID);
        assertFalse(userRepository.findById(ID).isPresent());
    }

    @Test
    void findUserInfo() {
        final UserLoginResponse response = userService.findUserInfo("test");
        assertThat(response.getUserId()).isEqualTo("test");
        assertThat(response.getPassword()).isEqualTo("1234");
    }
}