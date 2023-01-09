package com.june.sample.userservice.user.service;

import com.june.sample.userservice.core.exception.ValidationException;
import com.june.sample.userservice.user.domain.dto.UserLoginDTO;
import com.june.sample.userservice.user.domain.dto.UserRegDTO;
import com.june.sample.userservice.user.domain.dto.UserSearchDTO;
import com.june.sample.userservice.user.domain.model.UserEntity;
import com.june.sample.userservice.user.domain.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    @Value("${common.certification.code:}")
    private String certificationCode;

    @Override
    public boolean createUser(UserRegDTO userDto) {
        UserEntity buildUser = UserEntity.builder()
                .email(userDto.getEmail())
                .nickName(userDto.getNickName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .userName(userDto.getUserName())
                .phoneNumber(userDto.getPhoneNumber())
                .role(userDto.getRole())
                .build();

        userRepository.save(buildUser);

        return true;
    }

    @Override
    public UserSearchDTO searchUserByUserName(String name) {
        UserEntity user = userRepository.findByUserName(name);
        return modelMapper.map(user, UserSearchDTO.class);
    }

    @Override
    public List<UserSearchDTO> getUserAll() {
        List<UserEntity> userList = userRepository.findAll();
        List<UserSearchDTO> userDTOList = new ArrayList<>();
        userList.forEach(user -> userDTOList.add(modelMapper.map(user, UserSearchDTO.class)));
        return userDTOList;
    }

    @Override
    public UserLoginDTO getUserDetailByEmail(String email) {
        return new ModelMapper().map(userRepository.findByEmail(email).get(), UserLoginDTO.class);
    }

    @Override
    public String getCertificationCodeByUserPhoneNumber(String phoneNumber) {
        Optional<UserEntity> byPhoneNumber = userRepository.findByPhoneNumber(phoneNumber);
        if(byPhoneNumber.isPresent()){
            throw ValidationException
                    .withUserMessage("이미 등록된 연락처가 있습니다.")
                    .build();
        }
        return certificationCode;
    }

}
