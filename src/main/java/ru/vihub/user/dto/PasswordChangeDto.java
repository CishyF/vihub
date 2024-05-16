package ru.vihub.user.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeDto {

  private String oldPassword;

  @Size(min = 4, message = "Пароль должен содержать минимум 4 символа")
  @Size(max = 20, message = "Пароль не должен содержать более 20 символов")
  @Pattern(regexp = "^[a-zA-Z0-9]{4,20}$", message = "Пароль некорректен, попробуйте другой!")
  private String newPassword;

  private String newPasswordConfirm;
}
