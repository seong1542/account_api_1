package com.nhnacademy.account_api_1.response;

import com.nhnacademy.account_api_1.entity.Status;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class UserEmailResponse {
    private String userId;
    private String email;

}
