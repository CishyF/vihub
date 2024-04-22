package ru.vihub.security.service;

import ru.vihub.security.dto.LoginDtoRequest;
import ru.vihub.security.dto.RegistrationDtoRequest;

public interface AuthenticationService {

    String register(RegistrationDtoRequest dto);

    String login(LoginDtoRequest dto);
}
