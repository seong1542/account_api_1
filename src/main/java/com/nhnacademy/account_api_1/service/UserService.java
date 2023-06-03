package com.nhnacademy.account_api_1.service;

import com.nhnacademy.account_api_1.repository.UserRepository;
import com.nhnacademy.account_api_1.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserResponse> getUsers(){
        return userRepository.findAll();
    }
}
