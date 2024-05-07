package ru.vihub.security.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vihub.security.dto.LoginDtoRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/auth/login")
    public String login() {
        log.info("Пришел GET-запрос /auth/login без тела");
        return "login";
    }

    @PostMapping("/auth/login")
    public String login(@ModelAttribute("loginDtoRequest") LoginDtoRequest loginDtoRequest,
                            Model model, HttpServletRequest request) throws ServletException {
        model.addAttribute(loginDtoRequest);
        log.info("Пришел POST-запрос /auth/login с телом={}", loginDtoRequest);
        request.login(loginDtoRequest.getUsername(), loginDtoRequest.getPassword());
        log.info("Успешный вход и перенаправление POST-запроса /auth/login с телом={} в /home", loginDtoRequest);
        return "redirect:/home";
    }

    @GetMapping("auth/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/home";
    }
}
