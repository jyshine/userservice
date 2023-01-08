package com.june.sample.userservice.user.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "users")
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String userName;

    @NotEmpty
    private String nickName;

    @NotEmpty
    private String password;

    @NotEmpty
    private String phoneNumber;


}
