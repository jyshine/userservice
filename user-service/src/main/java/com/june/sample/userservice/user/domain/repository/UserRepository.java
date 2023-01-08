package com.june.sample.userservice.user.domain.repository;

import com.june.sample.userservice.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);


}
