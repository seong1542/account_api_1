package com.nhnacademy.account_api_1.controller;

import com.nhnacademy.account_api_1.request.UserModify;
import com.nhnacademy.account_api_1.response.UserResponse;
import com.nhnacademy.account_api_1.request.UserRequest;
import com.nhnacademy.account_api_1.request.UserStatus;
import com.nhnacademy.account_api_1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{indexId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(@PathVariable("indexId") Long id){
        return userService.getUser(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        return userService.insertUser(userRequest);
    }

    @PutMapping("/{indexId}")
    public void modifyUser(@PathVariable("indexId") Long id, @RequestBody UserModify userModify){
        userService.updateUser(id, userModify);
    }

    @PutMapping("/withdrawal/{indexId}")
    @ResponseStatus(HttpStatus.OK)
    public void modifyUserStatus(@RequestBody UserStatus status, @PathVariable("indexId") Long id){
        userService.updateUserStatus(id, status);
    }

    @DeleteMapping("/withdrawal/{indexId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("indexId") Long id){
        userService.removeUser(id);
    }

}
