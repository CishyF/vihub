package ru.vihub.security.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginUserDtoRequest {

    private String login;
    private String password;
}
