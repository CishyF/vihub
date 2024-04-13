package ru.vihub.security.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.vihub.security.dto.RegistrationUserDtoRequest;

@Slf4j
@Controller
@RequestMapping("/sign-up")
public class RegistrationController {

    @PostMapping
    public String createUser(@Valid @ModelAttribute RegistrationUserDtoRequest registrationUserDtoRequest) {
        log.info("dto: {}", registrationUserDtoRequest);
        return "home";
    }
}
