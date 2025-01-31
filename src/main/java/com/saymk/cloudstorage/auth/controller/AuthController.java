package com.saymk.cloudstorage.auth.controller;

import com.saymk.cloudstorage.auth.dto.request.UserLoginRequestDTO;
import com.saymk.cloudstorage.auth.dto.request.UserSignUpRequestDTO;
import com.saymk.cloudstorage.auth.exception.UserAlreadyExistException;
import com.saymk.cloudstorage.auth.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final RegistrationService registrationService;

    @GetMapping("login")
    public String login(@ModelAttribute("userDTO") UserLoginRequestDTO userDTO) {
        return "login";
    }


    @GetMapping("registration")
    public String registration(@ModelAttribute("userDTO") UserSignUpRequestDTO userDTO) {
        return "registration";
    }

    @PostMapping("registration")
    public String registration(@ModelAttribute("userDTO") @Valid UserSignUpRequestDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        try {
            registrationService.register(userDTO);
        } catch (UserAlreadyExistException e) {
            bindingResult.rejectValue("username", "", "Пользователь c таким именем уже существует");
            return "registration";
        }
        return "redirect:/home";
    }
}
