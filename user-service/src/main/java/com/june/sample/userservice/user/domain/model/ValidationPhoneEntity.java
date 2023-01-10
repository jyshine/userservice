package com.june.sample.userservice.user.domain.model;

import com.june.sample.userservice.core.enums.user.CertiType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "validation_phone")
@Getter
@NoArgsConstructor
public class ValidationPhoneEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    String phoneNumber;

    @Setter
    @Enumerated(EnumType.STRING)
    CertiType certiType;

    @Setter
    String certificationCode;

    @Builder
    public ValidationPhoneEntity(String phoneNumber, CertiType certiType, String certificationCode) {
        this.phoneNumber = phoneNumber;
        this.certiType = certiType;
        this.certificationCode = certificationCode;
    }
}
