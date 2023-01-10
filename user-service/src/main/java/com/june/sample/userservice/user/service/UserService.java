package com.june.sample.userservice.user.service;

import com.june.sample.userservice.user.domain.dto.UserCodeDTO;
import com.june.sample.userservice.user.domain.dto.UserLoginDTO;
import com.june.sample.userservice.user.domain.dto.UserRegDTO;
import com.june.sample.userservice.user.domain.dto.UserSearchDTO;
import java.util.List;

public interface UserService {
    boolean createUser(UserRegDTO userDto);

    UserSearchDTO searchUserByUserName(String name);

    List<UserSearchDTO> getUserAll();

    UserLoginDTO getUserDetailByEmail(String email);

    UserCodeDTO getCertificationCodeByUserPhoneNumber(String phoneNumber);

    boolean checkCertificationCode(UserCodeDTO userCodeDTO);

    boolean changeUserPassword(UserRegDTO userRegDTO);

    UserSearchDTO searchUserInfo(String phoneNumber);
}
