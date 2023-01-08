package com.june.sample.userservice.user.controller;

import static com.june.sample.userservice.core.web.Path.USERS;

import com.june.sample.userservice.user.domain.dto.UserRegDTO;
import com.june.sample.userservice.user.domain.dto.UserSearchDTO;
import com.june.sample.userservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = USERS)
    public Boolean createUser(@RequestBody UserRegDTO userDTO){
        return userService.createUser(userDTO);
    }

    @GetMapping(value = USERS)
    public UserSearchDTO searchUser(@RequestParam String userName) {
        return userService.searchUserByName(userName);
    }

}
