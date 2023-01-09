package com.june.sample.userservice.user.domain.dto;

import lombok.Data;

@Data
public class UserLoginDTO {

    private String email;

    private String password;

    private String userName;

    private String nickName;

    private String phoneNumber;
}
