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

    @Size(min = 1, message = "Username must contains at least 1 symbol!")
    @Size(max = 20, message = "Username must contains less than 20 symbols!")
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{1,20}$", message = "Username is incorrect, try another!")
    private String username;

    @Size(min = 4, message = "Password must contains at least 4 symbol!")
    @Size(max = 20, message = "Password must contains less than 20 symbols!")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,20}$", message = "Password is incorrect, try another!")
    private String password;

    @Email(message = "Email is incorrect, try again!")
    private String email;

    @Pattern(regexp = "^[A-Za-z]{2,30}$", message = "Firstname is incorrect, try another!")
    private String firstname;

    @Pattern(regexp = "^[A-Za-z]{2,30}$", message = "Lastname is incorrect, try another!")
    private String lastname;
}
