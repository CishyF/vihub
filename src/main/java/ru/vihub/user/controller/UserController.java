package ru.vihub.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vihub.user.model.User;

@Controller
@RequiredArgsConstructor


public class UserController {
    @GetMapping("/profile")
    public String getProfile(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user",(User)principal);
        return "profile";
    }
}
