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
import ru.vihub.exception.EntityNotFoundException;
import ru.vihub.user.dto.PasswordChangeDto;
import ru.vihub.user.dto.UserDto;
import ru.vihub.user.service.UserService;

import java.util.Objects;

import static ru.vihub.user.mapper.UserMapper.mapToUserDto;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
  private final UserService userService;

  @GetMapping("/profile")
  public String getProfile(Model model) {
    model.addAttribute("user", mapToUserDto(userService.findCurrentUser()));
    return "profile";
  }

  @PostMapping("/profile")
  public String logOut(HttpServletRequest request) throws ServletException {
    request.logout();
    return "redirect:/auth/login";
  }

  @GetMapping("/profile/edit")
  public String getProfileEdit(Model model) {
    model.addAttribute("userForEdit", userService.findCurrentUser());
    model.addAttribute("userForDisplay", userService.findCurrentUser());
    return "profile-edit";
  }

  @PostMapping("/profile/edit")
  public String updateProfile(
      @Valid @ModelAttribute("userForEdit") UserDto userDto, BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("userForEdit", userDto);
      model.addAttribute("userForDisplay", userService.findCurrentUser());
      return "profile-edit";
    }
    try{
      if (userService.findUserByUsername(userDto.getUsername()) != null
          && !Objects.equals(userService.findCurrentUser().getUsername(), userDto.getUsername())) {
        model.addAttribute("error", "Пользовтаель с таким именем уже существует");
        model.addAttribute("userForDisplay", userService.findCurrentUser());
        return "profile-edit";
        }
    } catch(EntityNotFoundException ex){
      log.info("userDto {}", userDto);
      userService.updateUser(userDto);
      return "redirect:/profile";
    }
    userService.updateUser(userDto);
    return "redirect:/profile";
  }

  @GetMapping("/profile/password-change")
  public String getPasswordChange(Model model) {
    model.addAttribute("passwordChangeDto", new PasswordChangeDto());
    return "password-change";
  }

  @PostMapping("/profile/password-change")
  public String updatePassword(
      @Valid @ModelAttribute("passwordChangeDto") PasswordChangeDto passwordChangeDto,
      BindingResult result,
      Model model) {
    if (result.hasErrors()) {
      return "password-change";
    }
    if (userService.checkPassword(passwordChangeDto.getOldPassword())) {
      if (passwordChangeDto.getNewPassword().equals(passwordChangeDto.getNewPasswordConfirm())) {
        userService.changeUserPassword(passwordChangeDto.getNewPassword());
      } else {
        model.addAttribute("error", "Пароли не совпадают");
        return "password-change";
      }
    } else {
      model.addAttribute("error", "Неверный пароль");
      return "password-change";
    }
    return "redirect:/profile";
  }
}
