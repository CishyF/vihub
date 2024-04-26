package ru.vihub.security.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vihub.security.dto.LoginDtoRequest;
import ru.vihub.security.service.AuthenticationService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/auth/login")
public class LoginController {

    private final AuthenticationService authenticationService;

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public String loginPost(@ModelAttribute("loginDtoRequest") LoginDtoRequest loginDtoRequest,
                            Model model, HttpServletRequest request) throws ServletException {
        model.addAttribute(loginDtoRequest);
        log.info("dto: {}", loginDtoRequest);
        request.login(loginDtoRequest.getUsername(),loginDtoRequest.getPassword());
        log.info("login!");
        return "redirect:/home";
    }
}
