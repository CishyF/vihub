package ru.vihub.security.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
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
        log.info("Пришел GET-запрос /auth/registration без тела");
        return "registration";
    }

    @PostMapping
    public String register(@Valid @ModelAttribute("registrationDtoRequest") RegistrationDtoRequest registrationDtoRequest,
                           Model model,HttpServletRequest request) throws ServletException {
        model.addAttribute(registrationDtoRequest);
        log.info("Пришел POST-запрос /auth/registration с телом={}", registrationDtoRequest);
        authenticationService.register(registrationDtoRequest);
        request.login(registrationDtoRequest.getUsername(), registrationDtoRequest.getPassword());
        log.info("Успешная регистрация и перенаправление POST-запроса /auth/registration с телом={} в /home", registrationDtoRequest);
        return "redirect:/home";
    }
}
