package com.nhnacademy.account_api_1.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class UserLoginResponse {

    private final String userId;
    private final String password;

    public UserLoginResponse(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
