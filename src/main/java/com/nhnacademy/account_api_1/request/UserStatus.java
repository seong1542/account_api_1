package com.nhnacademy.account_api_1.request;

import com.nhnacademy.account_api_1.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserStatus {
    private Status.StatusName status;

}
