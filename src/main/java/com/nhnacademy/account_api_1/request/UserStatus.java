package com.nhnacademy.account_api_1.request;

import com.nhnacademy.account_api_1.entity.Status;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserStatus {
    private Status.StatusName status;
}
