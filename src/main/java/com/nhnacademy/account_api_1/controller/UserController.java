package com.nhnacademy.account_api_1.controller;

import com.nhnacademy.account_api_1.request.UserModify;
import com.nhnacademy.account_api_1.request.UserRequest;
import com.nhnacademy.account_api_1.request.UserStatus;
import com.nhnacademy.account_api_1.response.UserResponse;
import com.nhnacademy.account_api_1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable("userId") String id){
        return userService.getUser(id);
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> createUser(@Valid @RequestBody UserRequest userRequest){
        return userService.insertUser(userRequest);
    }

    @PutMapping("/{userId}/change/info")
    public void modifyUser(@PathVariable("userId") String id,
                           @RequestBody UserModify userModify){
        userService.updateUser(id, userModify);
    }

    @PatchMapping("/{userId}/change/state")
    public void modifyUserStatus(@RequestBody UserStatus status, @PathVariable("userId") String id){
        userService.updateUserStatus(id, status);
    }
}
