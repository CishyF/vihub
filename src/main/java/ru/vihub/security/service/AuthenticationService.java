package ru.vihub.security.service;

import ru.vihub.security.dto.RegistrationDtoRequest;

public interface AuthenticationService{

    void register(RegistrationDtoRequest dto);
}
