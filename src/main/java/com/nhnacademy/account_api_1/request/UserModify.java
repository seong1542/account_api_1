package com.nhnacademy.account_api_1.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserModify {
    private String password;
    private String email;
}
