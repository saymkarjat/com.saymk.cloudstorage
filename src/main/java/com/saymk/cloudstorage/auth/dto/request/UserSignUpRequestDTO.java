package com.saymk.cloudstorage.auth.dto.request;

import com.saymk.cloudstorage.auth.validator.PasswordsMatch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@PasswordsMatch
public record UserSignUpRequestDTO(
        @NotNull(message = "Поле не может быть null.")
        @NotBlank(message = "Поле не может быть пустым или содержать только пробелы.")
        @Size(min = 5, max = 15, message = "Логин должен быть от 5 до 10 символов.")
        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Логин должен содержать только латинские буквы и цифры.")
        String username,
        @NotNull(message = "Поле не может быть null.")
        @NotBlank(message = "Поле не может быть пустым или содержать только пробелы.")
        @Size(min = 8, max = 25, message = "Пароль должен иметь от 8 до 25 символов")
        String password,
        @NotNull(message = "Поле не может быть null.")
        @NotBlank(message = "Поле не может быть пустым или содержать только пробелы.")
        String confirmPassword) {
}
