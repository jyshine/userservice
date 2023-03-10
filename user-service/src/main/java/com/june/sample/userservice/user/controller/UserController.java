package com.june.sample.userservice.user.controller;

import static com.june.sample.userservice.core.web.Path.USERS;
import static com.june.sample.userservice.core.web.Path.USERS_CHANGE_PASSWORD;
import static com.june.sample.userservice.core.web.Path.USERS_SEARCH;
import static com.june.sample.userservice.core.web.Path.USERS_SEND_CODE;
import static com.june.sample.userservice.core.web.Path.USERS_SEND_CODE_CHECK;

import com.june.sample.userservice.core.web.RestResponse;
import com.june.sample.userservice.user.domain.dto.UserCodeDTO;
import com.june.sample.userservice.user.domain.dto.UserRegDTO;
import com.june.sample.userservice.user.domain.dto.UserSearchDTO;
import com.june.sample.userservice.user.service.UserService;
import com.sun.xml.bind.v2.TODO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "회원관리")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "회원 가입")
    @PostMapping(value = USERS)
    public RestResponse<Boolean> createUser(@Valid @RequestBody UserRegDTO userDTO) {
        return new RestResponse<>(userService.createUser(userDTO));
    }

    @ApiOperation(value = "내 정보 보기")
    @GetMapping(value = USERS_SEARCH)
    public RestResponse<UserSearchDTO> searchUserByUserName(@RequestParam String phoneNumber) {
        return new RestResponse<>(userService.searchUserInfo(phoneNumber));
    }

    @ApiOperation(value = "전화번호 인증 전송 (임시)")
    @PostMapping(value = USERS_SEND_CODE)
    public RestResponse<UserCodeDTO> sendCode(@RequestBody UserCodeDTO userCodeDTO) {
        return new RestResponse<>(userService.getCertificationCodeByUserPhoneNumber(userCodeDTO.getPhoneNumber()));
    }

    @ApiOperation(value = "전화번호 인증 번호 확인 (임시)")
    @PostMapping(value = USERS_SEND_CODE_CHECK)
    public RestResponse<Boolean> sendCodeCheck(@RequestBody UserCodeDTO userCodeDTO) {
        return new RestResponse<>(userService.checkCertificationCode(userCodeDTO));
    }

    @ApiOperation(value = "비밀번호 찾기 (재설정)")
    @PostMapping(value = USERS_CHANGE_PASSWORD)
    public RestResponse<Boolean> changeUserPassword(@RequestBody UserRegDTO userRegDTO) {
        return new RestResponse<>(userService.changeUserPassword(userRegDTO));
    }
}
