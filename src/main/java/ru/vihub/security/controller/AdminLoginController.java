package ru.vihub.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.vihub.security.dto.LoginUserDtoRequest;

@Slf4j
@Controller
@SessionAttributes
@RequestMapping("/admin/login")
public class AdminLoginController {

    @PostMapping
    public String createUser(@ModelAttribute LoginUserDtoRequest loginUserDtoRequest) {
        log.info("dto: {}", loginUserDtoRequest);
        return "home";
    }
}
