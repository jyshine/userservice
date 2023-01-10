package com.june.sample.userservice.user.domain.repository;

import com.june.sample.userservice.core.enums.user.CertiType;
import com.june.sample.userservice.user.domain.model.ValidationPhoneEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValidationPhoneRepository extends JpaRepository<ValidationPhoneEntity, Long> {
    Optional<ValidationPhoneEntity> findByPhoneNumber(String phoneNumber);

    Optional<ValidationPhoneEntity> findByPhoneNumberAndCertiType(String phoneNumber, CertiType sendCode);

}
