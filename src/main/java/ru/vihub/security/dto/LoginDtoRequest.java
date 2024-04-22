package ru.vihub.security.dto;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginDtoRequest {

    private String username;
    private String password;
}
