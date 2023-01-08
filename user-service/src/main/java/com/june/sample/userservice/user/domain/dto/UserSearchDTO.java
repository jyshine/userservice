package com.june.sample.userservice.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSearchDTO {
    private Long id;

    private String name;

    private String nickName;

    private String phoneNumber;
}
