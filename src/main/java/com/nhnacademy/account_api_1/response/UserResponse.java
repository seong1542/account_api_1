package com.nhnacademy.account_api_1.response;

import com.nhnacademy.account_api_1.entity.Status;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class UserResponse {
    private String userId;
    private String password;
    private Status.StatusName status;
    private String email;

    @QueryProjection
    public UserResponse(String userId, String password, Status.StatusName status, String email){
        this.userId = userId;
        this.password = password;
        this.status = status;
        this.email = email;
    }
}