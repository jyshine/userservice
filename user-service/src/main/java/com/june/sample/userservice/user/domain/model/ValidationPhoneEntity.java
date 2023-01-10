package com.june.sample.userservice.user.domain.model;

import com.june.sample.userservice.core.enums.user.PhoneValidationType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;


@Entity(name = "validation_phone")
@Getter
public class ValidationPhoneEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String phoneNumber;

    PhoneValidationType phoneValidationType;

}
