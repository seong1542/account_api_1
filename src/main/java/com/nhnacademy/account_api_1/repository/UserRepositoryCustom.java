package com.nhnacademy.account_api_1.repository;

import com.nhnacademy.account_api_1.response.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom {
    List<UserResponse> getUserResponseList();
    UserResponse getUserResponseByIndexId(Long id);
}
