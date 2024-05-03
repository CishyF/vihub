package ru.vihub.user.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping("/profile")
    public String logOut(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/auth/login";
    }
}

