package com.june.sample.userservice.user.domain.dto;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegDTO {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String userName;

    @NotEmpty
    private String nickName;

    @NotEmpty
    private String phoneNumber;
}
