package com.nhnacademy.account_api_1.controller;

import com.nhnacademy.account_api_1.request.UserStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @GetMapping
    public List<Users> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{indexId}")
    public User getUser(){
        return userService.getUser();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        return userService.insertUser();
    }

    @PutMapping("/withdrawal/{indexId}")
    public Map<String, String> updateUser(@RequestBody UserStatus status){
        userService.updateUserStatus(status);
        return Map.of("status", status.toString());
    }

    @DeleteMapping("/withdrawal/{indexId}")
    public void deleteUser(){

    }
}
