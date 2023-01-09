package com.june.sample.userservice.user.controller;

import static com.june.sample.userservice.core.web.Path.USERS;
import static com.june.sample.userservice.core.web.Path.USERS_SEARCH;

import com.june.sample.userservice.core.web.RestResponse;
import com.june.sample.userservice.user.domain.dto.UserRegDTO;
import com.june.sample.userservice.user.domain.dto.UserSearchDTO;
import com.june.sample.userservice.user.service.UserService;
import javax.validation.Valid;
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
    public RestResponse<Boolean> createUser(@Valid @RequestBody UserRegDTO userDTO) {
        return new RestResponse<>(userService.createUser(userDTO));
    }

    @GetMapping(value = USERS_SEARCH)
    public RestResponse<UserSearchDTO> searchUserByUserName(@RequestParam String userName) {
        return new RestResponse<>(userService.searchUserByUserName(userName));
    }

}
