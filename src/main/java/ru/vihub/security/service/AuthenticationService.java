package ru.vihub.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.vihub.security.dto.LoginDtoRequest;
import ru.vihub.security.dto.RegistrationDtoRequest;

public interface AuthenticationService{

    void register(RegistrationDtoRequest dto);

    void login(LoginDtoRequest dto);
}
