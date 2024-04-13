package ru.vihub.security.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDtoRequest {

    private String login;
    private String password;
}
