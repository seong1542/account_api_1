package com.nhnacademy.account_api_1.request;

import com.nhnacademy.account_api_1.entity.Status;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = "아이디를 입력하세요")
    private String userId;
    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;
    @Email(message = "이메일 형식으로 입력하세요")
    private String email;

}
