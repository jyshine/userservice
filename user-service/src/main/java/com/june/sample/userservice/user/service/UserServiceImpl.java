package com.june.sample.userservice.user.service;

import com.june.sample.userservice.user.domain.dto.UserRegDTO;
import com.june.sample.userservice.user.domain.dto.UserSearchDTO;
import com.june.sample.userservice.user.domain.model.User;
import com.june.sample.userservice.user.domain.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public boolean createUser(UserRegDTO userDto) {
        User buildUser = User.builder()
                .email(userDto.getEmail())
                .nickName(userDto.getNickName())
                .password(userDto.getPassword())
                .userName(userDto.getUserName())
                .phoneNumber(userDto.getPassword())
                .build();

        userRepository.save(buildUser);

        return true;
    }

    @Override
    public UserSearchDTO searchUserByUserName(String name) {
        User user = userRepository.findByUserName(name);
        return modelMapper.map(user, UserSearchDTO.class);
    }

    @Override
    public List<UserSearchDTO> searchUserAll() {
        List<User> userList = userRepository.findAll();
        List<UserSearchDTO> userDTOList = new ArrayList<>();
        userList.forEach(user -> {
            userDTOList.add(modelMapper.map(user, UserSearchDTO.class));
        });
        return userDTOList;
    }

}
