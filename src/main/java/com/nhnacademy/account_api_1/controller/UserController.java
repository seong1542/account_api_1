package com.nhnacademy.account_api_1.controller;

import com.nhnacademy.account_api_1.request.UserModify;
import com.nhnacademy.account_api_1.request.UserRequest;
import com.nhnacademy.account_api_1.request.UserStatus;
import com.nhnacademy.account_api_1.response.UserResponse;
import com.nhnacademy.account_api_1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

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
    public Optional<UserResponse> getUser(@PathVariable("userId") String id){
        return userService.getUser(id);
    }

    @GetMapping("/email/{email}")
    public Optional<UserResponse> getUserByEmail(@PathVariable("email") String email){
        return userService.getUserByEmail(email);
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody UserRequest userRequest,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException();
        }
        userService.insertUser(userRequest);
    }

    @PutMapping("/change/info/{userId}")
    public void modifyUser(@PathVariable("userId") String id,
                           @RequestBody @Valid UserModify userModify,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException();
        }

        userService.updateUser(id, userModify);
    }

    @PostMapping("/change/state/{userId}")
    public void modifyUserStatus(@RequestBody UserStatus status,
                                 @PathVariable("userId") String id){
        userService.updateUserStatus(id, status);
    }
}
