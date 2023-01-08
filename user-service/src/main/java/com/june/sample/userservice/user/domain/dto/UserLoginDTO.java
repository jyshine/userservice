package com.june.sample.userservice.user.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserLoginDTO {

    private String email;

    private String password;

    private String userName;

    private String nickName;

    private String phoneNumber;
}
