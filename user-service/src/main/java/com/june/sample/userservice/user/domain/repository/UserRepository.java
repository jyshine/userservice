package com.june.sample.userservice.user.domain.repository;

import com.june.sample.userservice.user.domain.model.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserName(String name);

    Optional<UserEntity> findByEmail(String email);


}
