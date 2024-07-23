package ru.vihub.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDtoRequest {

    @Size(min = 1, message = "Имя пользователя должно содержать как минимум 1 символ")
    @Size(max = 20, message = "Имя пользователя не должно содержпть более 20 символов")
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{1,20}$", message = "Имя пользователя некорректно, попробуйте другое!")
    private String username;

    @Size(min = 4, message = "Пароль должен содержать минимум 4 символа")
    @Size(max = 20, message = "Пароль не должен содержать более 20 символов")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,20}$", message = "Пароль некорректен, попробуйте другой!")
    private String password;

    @Email(message = "Почта некорректна, попробуйте другую!")
    private String email;

    @Pattern(regexp = "^[А-Яа-яA-Za-z]{2,30}$", message = "Имя некорректно, попробуйте другое!")
    private String firstname;

    @Pattern(regexp = "^[А-Яа-яA-Za-z]{2,30}$", message = "Фамилия некорректна, попробуйте другую!")
    private String lastname;
}
