package com.june.sample.userservice.core.security;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestLogin {
    @NotNull()
    @Size(min = 2)
    @Email
    private String email;

    @NotNull()
    @Size(min = 8)
    private String password;
}
