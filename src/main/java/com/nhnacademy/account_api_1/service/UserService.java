package com.nhnacademy.account_api_1.service;

import com.nhnacademy.account_api_1.entity.Status;
import com.nhnacademy.account_api_1.exception.AlreadyUserException;
import com.nhnacademy.account_api_1.exception.NotFoundDataException;
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
import java.util.Optional;

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
    public Optional<UserResponse> getUser(String id){
        return userRepository.getUserResponseByUserId(id);
    }

    public void insertUser(UserRequest userRequest){
        if(userRepository.existsByUserId(userRequest.getUserId()) || userRepository.existsByEmail(userRequest.getEmail())){
            throw new AlreadyUserException("이미 존재하는 유저입니다.");
        }
        userRepository.save(new User(userRequest.getUserId(), userRequest.getPassword(), userRequest.getEmail(), statusRepository.getReferenceById(1)));
    }

    public void updateUser(String id, UserModify userModify){
        User user = userRepository.findUserByUserId(id).orElseThrow(
                () -> new NotFoundDataException("존재하는 유저가 아닙니다."));
        user.updateUser(userModify.getPassword(), userModify.getEmail());
    }

    public void updateUserStatus(String id, UserStatus userStatus){
        User user = userRepository.findUserByUserId(id).orElseThrow(
                () -> new NotFoundDataException("존재하는 유저가 아닙니다.")
        );
        Status.StatusName statusName = Status.StatusName.valueOf(userStatus.getStatus());
        user.setStatus(statusRepository.getReferenceById(statusName.getNumberOfStatus()));
    }

    public Optional<UserResponse> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
