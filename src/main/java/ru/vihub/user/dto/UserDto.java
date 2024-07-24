package ru.vihub.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
  private long id;

  @Size(min = 1, message = "Имя пользователя должно содержать как минимум 1 символ")
  @Size(max = 20, message = "Имя пользователя не должно содержпть более 20 символов")
  @Pattern(
      regexp = "^[A-Za-z][A-Za-z0-9_]{1,20}$",
      message = "Имя пользователя некорректно, попробуйте другое!")
  private String username;

  @Size(min = 4, message = "Пароль должен содержать минимум 4 символа")
  @Size(max = 20, message = "Пароль не должен содержать более 20 символов")
  @Pattern(regexp = "^[a-zA-Z0-9]{4,20}$", message = "Пароль некорректен, попробуйте другой!")
  private String password;

  @Email(message = "Почта некорректна, попробуйте другую!")
  @Size(min = 4, message = "Почта должна содержать минимум 4 символа")
  @Size(max = 35, message = "Почта не должна содержать более 35 символов")
  private String email;

  @Pattern(regexp = "^[A-Za-zА-Яа-я]{2,30}$", message = "Имя некорректно, попробуйте другое!")
  private String firstname;

  @Pattern(regexp = "^[A-Za-zА-Яа-я]{2,30}$", message = "Фамилия некорректна, попробуйте другую!")
  private String lastname;

  @Pattern(
      regexp = "^([+]{1}(?:[0-9\\-()/.]\\s?){6,15}[0-9]{1}|)$",
      message = "Номер телефона некорректен, попробуйте другой!")
  private String phone;

  private String address;
  private String github;
  private String instagram;
  private String telegram;
  private String photoUrl;
}
