package com.june.sample.userservice.user.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Getter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
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

    @Builder
    public UserEntity(String email, String userName, String nickName, String password, String phoneNumber) {
        this.email = email;
        this.userName = userName;
        this.nickName = nickName;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

}
