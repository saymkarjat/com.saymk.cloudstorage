package com.saymk.cloudstorage.auth.service;

import com.saymk.cloudstorage.auth.dto.request.UserSignUpRequestDTO;
import com.saymk.cloudstorage.auth.exception.UserAlreadyExistException;
import com.saymk.cloudstorage.auth.model.User;
import com.saymk.cloudstorage.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class RegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(UserSignUpRequestDTO userDTO) {
        User user = User.builder()
                .username(userDTO.username())
                .password(passwordEncoder.encode(userDTO.password()))
                .role("ROLE_USER")
                .build();

        try {
            userRepository.save(user);
            log.info("User {} successfully registered", userDTO.username());
        } catch (DataIntegrityViolationException e) {
            log.warn("Registration failed: user {} already exists", userDTO.username());
            throw new UserAlreadyExistException();
        }
    }
}
