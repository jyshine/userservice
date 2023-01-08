package com.june.sample.userservice.user.service;

import com.june.sample.userservice.user.domain.dto.UserRegDTO;
import com.june.sample.userservice.user.domain.dto.UserSearchDTO;
import java.util.List;

public interface UserService {
    boolean createUser(UserRegDTO userDto);

    UserSearchDTO searchUserByUserName(String name);

    List<UserSearchDTO> getUserAll();

    UserSearchDTO getUserDetailByEmail(String email);

}
