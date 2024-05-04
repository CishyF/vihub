package ru.vihub.user.controller;


import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vihub.user.dto.UserDto;
import ru.vihub.user.service.UserService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public String getProfile(Model model){
        model.addAttribute("user",userService.findCurrentUser());
        return "profile";
    }
    @PostMapping("/profile")
    public String logOut(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/auth/login";
    }
    @GetMapping("/profile/edit")
    public String getProfileEdit(Model model){

        model.addAttribute("user",userService.findCurrentUser());
        return "profile-edit";
    }
    @PostMapping("/profile/edit")
    public String updateProfile(@Valid @ModelAttribute("user")UserDto userDto,
                                BindingResult result, Model model)  {
        if (result.hasErrors()){
            model.addAttribute("user",userDto);
            return "profile-edit";
        }
        log.info("userDto {}",userDto);
        userService.updateUser(userDto);
        return "redirect:/profile";
    }
}

