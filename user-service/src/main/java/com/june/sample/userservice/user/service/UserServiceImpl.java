package com.june.sample.userservice.user.service;

import com.june.sample.userservice.core.enums.user.UserRoleType;
import com.june.sample.userservice.core.exception.BizException;
import com.june.sample.userservice.core.exception.ValidationException;
import com.june.sample.userservice.user.domain.dto.UserCodeDTO;
import com.june.sample.userservice.user.domain.dto.UserLoginDTO;
import com.june.sample.userservice.user.domain.dto.UserRegDTO;
import com.june.sample.userservice.user.domain.dto.UserSearchDTO;
import com.june.sample.userservice.user.domain.model.UserEntity;
import com.june.sample.userservice.user.domain.repository.UserRepository;
import java.time.LocalDateTime;
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
                .role(UserRoleType.N)
                .build();
        buildUser.setCreatedDate(LocalDateTime.now());
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
    public UserCodeDTO getCertificationCodeByUserPhoneNumber(String phoneNumber) {
        Optional<UserEntity> byPhoneNumber = userRepository.findByPhoneNumber(phoneNumber);
        UserCodeDTO userCodeDTO = new UserCodeDTO();
        if(byPhoneNumber.isPresent()){
            throw ValidationException
                    .withUserMessage("이미 등록된 연락처가 있습니다.")
                    .build();
        }


        userCodeDTO.setCode(certificationCode);
        userCodeDTO.setPhoneNumber(phoneNumber);

        return userCodeDTO;
    }

    @Override
    public boolean checkCertificationCode(UserCodeDTO userCodeDTO) {
        Optional<UserEntity> byPhoneNumber = userRepository.findByPhoneNumber(userCodeDTO.getPhoneNumber());

        if(byPhoneNumber.isPresent() && userCodeDTO.getCode().equals(certificationCode)) {
            UserEntity user = byPhoneNumber.get();

            if (!user.getRole().equals(UserRoleType.N)){
                throw BizException
                        .withUserMessageKey("api.users.code.check")
                        .build();
            }
            user.setUpdatedDate(LocalDateTime.now());
            user.setRole(UserRoleType.U);
            userRepository.save(user);

            return true;
        }
        return false;
    }

}
