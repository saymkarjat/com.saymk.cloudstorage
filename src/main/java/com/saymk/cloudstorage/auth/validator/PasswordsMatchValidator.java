package com.saymk.cloudstorage.auth.validator;

import com.saymk.cloudstorage.auth.dto.request.UserSignUpRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, UserSignUpRequestDTO> {

    @Override
    public boolean isValid(UserSignUpRequestDTO user, ConstraintValidatorContext context) {
        if (user == null) {
            return true;
        }

        boolean isValid = user.password().equals(user.confirmPassword());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Пароли не совпадают")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
        }

        return isValid;
    }
}