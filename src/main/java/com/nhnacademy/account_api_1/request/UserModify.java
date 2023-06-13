package com.nhnacademy.account_api_1.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserModify {
    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;
    @NotBlank(message = "이메일을 입력하세요")
    private String email;
}
