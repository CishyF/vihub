package ru.vihub.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vihub.security.dto.LoginUserDtoRequest;

@Slf4j
@Controller
@SessionAttributes
@RequestMapping("/admin/login")
public class AdminLoginController {
    @GetMapping
    public String getLoginPage(){
        return "login";
    }

    @PostMapping
    public String createUser(@ModelAttribute("loginUserDtoRequest") LoginUserDtoRequest loginUserDtoRequest, Model model) {
        model.addAttribute("loginUserDtoRequest", loginUserDtoRequest);
        log.info("dto: {}", loginUserDtoRequest);
        return "login";
    }
}
