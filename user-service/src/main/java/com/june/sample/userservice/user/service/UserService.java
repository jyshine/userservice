package com.june.sample.userservice.user.service;

import com.june.sample.userservice.user.domain.dto.UserLoginDTO;
import com.june.sample.userservice.user.domain.dto.UserRegDTO;
import com.june.sample.userservice.user.domain.dto.UserSearchDTO;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean createUser(UserRegDTO userDto);

    UserSearchDTO searchUserByUserName(String name);

    List<UserSearchDTO> getUserAll();

    UserLoginDTO getUserDetailByEmail(String email);

}
