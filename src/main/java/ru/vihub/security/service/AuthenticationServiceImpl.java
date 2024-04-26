package ru.vihub.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vihub.security.dto.RegistrationDtoRequest;
import ru.vihub.user.service.UserService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    @Override
    public void register(RegistrationDtoRequest dto) {
        userService.createUser(dto);
    }
}
