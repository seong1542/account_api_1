package com.nhnacademy.account_api_1.request;

import com.nhnacademy.account_api_1.entity.Status;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "아이디를 입력하세요")
    private String userId;
    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;
    private String email;

}
