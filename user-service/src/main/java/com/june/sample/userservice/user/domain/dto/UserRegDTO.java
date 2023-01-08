package com.june.sample.userservice.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegDTO {

    private String password;

    private String name;

    private String nickName;

    private String phoneNumber;
}
