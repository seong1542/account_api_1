package com.nhnacademy.account_api_1.service;

import com.nhnacademy.account_api_1.request.UserModify;
import com.nhnacademy.account_api_1.response.UserResponse;
import com.nhnacademy.account_api_1.entity.User;
import com.nhnacademy.account_api_1.repository.StatusRepository;
import com.nhnacademy.account_api_1.repository.UserRepository;
import com.nhnacademy.account_api_1.request.UserRequest;
import com.nhnacademy.account_api_1.request.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers(){
        return userRepository.getUserResponseList();
    }


    @Transactional(readOnly = true)
    public UserResponse getUser(String id){
        User user = userRepository.findByUserId(id).orElseThrow(
                () -> new IllegalArgumentException("No search User Data")
        );
        return new UserResponse(user.getUserId(), user.getPassword(), user.getStatus().getStatusName(), user.getEmail());
    }

    // TODO 1. Boolean.True를 사용한 이유?
    public Map<String, String> insertUser(UserRequest userRequest){
        if(userRepository.existsByUserId(userRequest.getUserId())){
            throw new IllegalArgumentException("Already Exists UserId");
        }

        userRepository.save(new User(userRequest.getUserId(), userRequest.getPassword(), userRequest.getEmail(), statusRepository.getReferenceById(1)));
        return Map.of("result", "회원가입되었습니다. 로그인해주세요.");
    }

    public void updateUser(String id, UserModify userModify){
        User user = userRepository.findByUserId(id).orElseThrow(
                () -> new IllegalArgumentException("No search User Data"));
        user.setPassword(userModify.getPassword());
        user.setEmail(userModify.getEmail());
    }

    public void updateUserStatus(String id, UserStatus userStatus){
        User user = userRepository.findByUserId(id).orElseThrow(
                () -> new IllegalArgumentException("No search User data")
        );
        user.setStatus(statusRepository.getReferenceById(userStatus.getStatus().getNumberOfStatus()));
    }
}
