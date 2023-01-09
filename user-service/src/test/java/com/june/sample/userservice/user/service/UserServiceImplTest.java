package com.june.sample.userservice.user.service;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.june.sample.UserServiceApplication;
import com.june.sample.userservice.core.enums.user.UserRoleType;
import com.june.sample.userservice.core.exception.ValidationException;
import com.june.sample.userservice.user.domain.dto.UserCodeDTO;
import com.june.sample.userservice.user.domain.dto.UserLoginDTO;
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
                .email("test@gmail.com")
                .userName("홍길동")
                .nickName("길동")
                .password("1234")
                .phoneNumber("010-1111-2222")
                .role(UserRoleType.U)
                .build();

        UserRegDTO userDTO2 = UserRegDTO.builder()
                .email("test2@gmail.com")
                .userName("김아무개")
                .nickName("김김")
                .password("1234")
                .phoneNumber("010-2222-2222")
                .role(UserRoleType.U)
                .build();

        userService.createUser(userDTO);
        userService.createUser(userDTO2);
    }

    @Test
    void 간단_회원가입_테스트(){
        UserRegDTO userDTO3 = UserRegDTO.builder()
                .email("test3@gmail.com")
                .userName("김사랑")
                .nickName("사랑")
                .password("1234")
                .phoneNumber("010-3333-2222")
                .role(UserRoleType.M)
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
        List<UserSearchDTO> userDTOS = userService.getUserAll();
        assertThat(userDTOS.size()).isGreaterThan(3);
    }

    @Test
    void 회원_조회_이메일(){
        UserLoginDTO userDTOS = userService.getUserDetailByEmail("test2@gmail.com");
        assertThat("김아무개").isEqualTo(userDTOS.getUserName());
    }

    @Test
    void 회원_전화번호_인증(){
        String phoneNumber = "010-3333-8888";
        UserCodeDTO certificationCodeByUserPhoneNumber = userService.getCertificationCodeByUserPhoneNumber(phoneNumber);
        assertThat("0000").isEqualTo(certificationCodeByUserPhoneNumber.getCode());
    }

    @Test
    void 회원_전화번호_중복(){
        UserRegDTO userDTO3 = UserRegDTO.builder()
                .email("test4@gmail.com")
                .userName("김호호")
                .nickName("하하")
                .password("1234")
                .phoneNumber("010-3333-2224")
                .role(UserRoleType.M)
                .build();

        userService.createUser(userDTO3);

        String phoneNumber2 = "010-3333-2224";

        assertThat(catchThrowable(() -> userService.getCertificationCodeByUserPhoneNumber(phoneNumber2))) // don't reduce to assertThatThrownBy because .as() won't work!
                .as("이미 등록된 연락처가 있습니다.")
                .isInstanceOf(ValidationException.class);
    }
}