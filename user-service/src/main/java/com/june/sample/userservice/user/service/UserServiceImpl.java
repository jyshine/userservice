package com.june.sample.userservice.user.service;

import com.june.sample.userservice.core.enums.user.CertiType;
import com.june.sample.userservice.core.enums.user.UserRoleType;
import com.june.sample.userservice.core.exception.ValidationException;
import com.june.sample.userservice.user.domain.dto.UserCodeDTO;
import com.june.sample.userservice.user.domain.dto.UserLoginDTO;
import com.june.sample.userservice.user.domain.dto.UserRegDTO;
import com.june.sample.userservice.user.domain.dto.UserSearchDTO;
import com.june.sample.userservice.user.domain.model.UserEntity;
import com.june.sample.userservice.user.domain.model.ValidationPhoneEntity;
import com.june.sample.userservice.user.domain.repository.UserRepository;
import com.june.sample.userservice.user.domain.repository.ValidationPhoneRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    ValidationPhoneRepository validationPhoneRepository;

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
        buildUser.setDeleted(false);
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
        Optional<ValidationPhoneEntity> phoneEntity = validationPhoneRepository.findByPhoneNumber(phoneNumber);

        if(phoneEntity.isPresent()){
            ValidationPhoneEntity findValidPhone = phoneEntity.get();
            findValidPhone.setCertiType(CertiType.SEND_CODE);
            findValidPhone.setCertificationCode(certificationCode);
            findValidPhone.setUpdatedDate(LocalDateTime.now());
            validationPhoneRepository.save(findValidPhone);
        }else {
            ValidationPhoneEntity validationPhoneEntity = ValidationPhoneEntity.builder()
                    .phoneNumber(phoneNumber)
                    .certificationCode(certificationCode)
                    .certiType(CertiType.SEND_CODE).build();
            validationPhoneEntity.setCreatedDate(LocalDateTime.now());
            validationPhoneEntity.setDeleted(false);
            validationPhoneRepository.save(validationPhoneEntity);
        }

        UserCodeDTO userCodeDTO = new UserCodeDTO();
        userCodeDTO.setCode(certificationCode);
        userCodeDTO.setPhoneNumber(phoneNumber);

        return userCodeDTO;
    }


    @Override
    public boolean checkCertificationCode(UserCodeDTO userCodeDTO) {
        ValidationPhoneEntity phoneEntity = validationPhoneRepository.
                findByPhoneNumberAndCertiType(userCodeDTO.getPhoneNumber(),CertiType.SEND_CODE)
                .orElseThrow(() ->
                        ValidationException
                        .withUserMessage("전화번호 인증 대상이 없습니다.")
                        .build());

        if (userCodeDTO.getCode().equals(phoneEntity.getCertificationCode())) {
            phoneEntity.setCertiType(CertiType.CHECKED);
            phoneEntity.setUpdatedDate(LocalDateTime.now());
            validationPhoneRepository.save(phoneEntity);
            return true;
        }
        return false;
    }

}
