package com.june.sample.userservice.user.validation;

import com.june.sample.userservice.user.domain.dto.UserRegDTO;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

public class BeanValidationTest {

    @Test
    void beanValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        UserRegDTO userRegDTO = new UserRegDTO();
        userRegDTO.setUserName("test");

        Set<ConstraintViolation<UserRegDTO>> violations = validator.validate(userRegDTO);
        for (ConstraintViolation<UserRegDTO> violation:violations){
            System.out.println("violation = "+ violation);
            System.out.println("violation = "+ violation.getMessage());
        }

    }
}
