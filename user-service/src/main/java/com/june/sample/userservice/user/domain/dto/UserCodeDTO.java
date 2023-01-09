package com.june.sample.userservice.user.domain.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.lang.Nullable;

@Data
public class UserCodeDTO {
    @NotNull
    String phoneNumber;

    @Nullable
    @NumberFormat
    @Size(max = 4)
    String code;
}
