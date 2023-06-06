package com.nhnacademy.account_api_1.request;

import com.nhnacademy.account_api_1.entity.Status;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "아이디를 입력하세요")
    private String userId;
    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;

    @NotBlank(message = "이메일을 입력하세요")
    private String email;

    public UserRequest(String userId, String password, String email){
        this.userId = userId;
        this.password = password;
        this.email =email;
    }
}
