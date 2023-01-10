package com.june.sample.userservice.user.domain.model;

import com.june.sample.userservice.core.enums.user.UserRoleType;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Getter
@NoArgsConstructor
public class UserEntity extends BaseEntity{
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

    /** 권한 */
    @Column(name="role")
    @Enumerated(EnumType.STRING)
    @Setter
    private UserRoleType role;

    @Builder
    public UserEntity(String email, String userName, String nickName, String password, String phoneNumber,UserRoleType role) {
        this.email = email;
        this.userName = userName;
        this.nickName = nickName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

}
