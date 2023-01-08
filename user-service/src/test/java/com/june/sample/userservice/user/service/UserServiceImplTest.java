package com.june.sample.userservice.user.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.june.sample.UserServiceApplication;
import com.june.sample.userservice.user.domain.dto.UserRegDTO;
import com.june.sample.userservice.user.domain.dto.UserSearchDTO;
import com.june.sample.userservice.user.domain.repository.UserRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest(classes = {UserServiceApplication.class})
@Transactional
@Rollback(value = false)
class UserServiceImplTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    void set_up(){
        UserRegDTO userDTO = UserRegDTO.builder()
                .userName("홍길동")
                .nickName("길동")
                .password("1234")
                .phoneNumber("010-1111-2222")
                .build();

        UserRegDTO userDTO2 = UserRegDTO.builder()
                .userName("김아무개")
                .nickName("김김")
                .password("1234")
                .phoneNumber("010-2222-2222")
                .build();

        userService.createUser(userDTO);
        userService.createUser(userDTO2);
    }

    @Test
    void 간단_회원가입_테스트(){
        UserRegDTO userDTO3 = UserRegDTO.builder()
                .userName("김사랑")
                .nickName("사랑")
                .password("1234")
                .phoneNumber("010-3333-2222")
                .build();
        boolean isSuccess = userService.createUser(userDTO3);
        assertThat(isSuccess).isTrue();
    }

    @Test
    void 회원_조회_테스트(){
        UserSearchDTO userDTO1 = userService.searchUserByUserName("홍길동");
        assertThat(userDTO1.getUserName()).isEqualTo("홍길동");
        assertThat(userDTO1.getNickName()).isEqualTo("길동");
    }

    @Test
    void 회원_리스트_조회(){
        List<UserSearchDTO> userDTOS = userService.searchUserAll();
        assertThat(userDTOS.size()).isEqualTo(3);
    }

}