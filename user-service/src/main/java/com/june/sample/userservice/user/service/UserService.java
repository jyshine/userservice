package com.june.sample.userservice.user.service;

import com.june.sample.userservice.user.domain.dto.UserRegDTO;
import com.june.sample.userservice.user.domain.dto.UserSearchDTO;
import java.util.List;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface UserService {
    boolean createUser(UserRegDTO userDto);

    UserSearchDTO searchUserByName(String name);

    List<UserSearchDTO> searchUserAll();
}
