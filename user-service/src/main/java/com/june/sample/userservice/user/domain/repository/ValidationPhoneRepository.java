package com.june.sample.userservice.user.domain.repository;

import com.june.sample.userservice.user.domain.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValidationPhoneRepository extends JpaRepository<UserEntity, Long> {
}
