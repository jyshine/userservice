package com.june.sample.userservice.user.domain.dto;

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

    private String userName;

    private String nickName;

    private String phoneNumber;
}
