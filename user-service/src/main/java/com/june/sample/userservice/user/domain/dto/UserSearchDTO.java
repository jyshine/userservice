package com.june.sample.userservice.user.domain.dto;

import com.june.sample.userservice.core.enums.user.UserRoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSearchDTO {
    private Long id;

    private String email;

    private String userName;

    private String nickName;

    private String phoneNumber;

    private UserRoleType role;
}
