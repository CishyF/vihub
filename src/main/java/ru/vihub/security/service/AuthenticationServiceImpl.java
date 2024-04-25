package ru.vihub.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vihub.security.dto.LoginDtoRequest;
import ru.vihub.security.dto.RegistrationDtoRequest;
import ru.vihub.user.model.User;
import ru.vihub.user.service.UserService;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    @Override
    public void register(RegistrationDtoRequest dto) {
        userService.createUser(dto);
    }

    @Override
    public void login(LoginDtoRequest dto) {

    }
}
