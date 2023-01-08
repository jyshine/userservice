package com.june.sample.userservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.june.sample.userservice.domain.User;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Transactional
@Rollback(value = false)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void DB_연결_회원등록_테스트(){
        User user = new User();
        user.setName("test");
        User save = userRepository.save(user);
        assertThat(user.getName()).isEqualTo(save.getName());
    }
}