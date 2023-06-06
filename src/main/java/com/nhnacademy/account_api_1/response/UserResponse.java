package com.nhnacademy.account_api_1.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private final String userId;
    private final String email;

    @QueryProjection
    public UserResponse(String userId, String email){
        this.userId = userId;
        this.email = email;
    }
}