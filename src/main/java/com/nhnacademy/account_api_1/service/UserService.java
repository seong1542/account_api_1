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

import java.util.List;

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
    public UserResponse getUser(Long id){
        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("No such User Data");
        }
        return userRepository.getUserResponseByIndexId(id);
    }


    public UserResponse insertUser(UserRequest userRequest){
        if(userRepository.existsByUserId(userRequest.getUserId())){
            throw new IllegalArgumentException("Already Exists UserId");
        }

        User user = userRepository.save(new User(userRequest.getUserId(), userRequest.getPassword(), userRequest.getEmail(), statusRepository.getReferenceById(1)));
        return new UserResponse(user.getUserId(), user.getEmail());
    }

    public void updateUser(Long id, UserModify userModify){
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No such User Data"));
        user.setPassword(userModify.getPassword());
        user.setEmail(userModify.getEmail());
    }

    public void updateUserStatus(Long id, UserStatus userStatus){
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No such User data"));
        user.setStatus(statusRepository.getReferenceById(userStatus.getStatus().getStatusCode()));
    }

    public void removeUser(Long id){
        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("No such User data");
        }
        userRepository.deleteById(id);
    }
}
