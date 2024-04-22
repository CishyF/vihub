package ru.vihub.security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vihub.security.dto.RegistrationDtoRequest;
import ru.vihub.security.service.AuthenticationService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/auth/registration")
public class RegistrationController {

    private final AuthenticationService authenticationService;

    @GetMapping
    public String register() {
        return "registration";
    }

    @PostMapping
    public String register(@Valid @ModelAttribute("registrationDtoRequest") RegistrationDtoRequest registrationDtoRequest, Model model) {
        model.addAttribute(registrationDtoRequest);
        log.info("dto: {}", registrationDtoRequest);
        String token = authenticationService.register(registrationDtoRequest);
        log.info("registered! token: {}", token);
        return "redirect:/home";
    }
}
