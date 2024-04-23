package ru.vihub.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.vihub.security.dto.LoginDtoRequest;
import ru.vihub.security.dto.RegistrationDtoRequest;
import ru.vihub.security.jwt.entity.Token;
import ru.vihub.security.jwt.service.JwtService;
import ru.vihub.user.model.User;
import ru.vihub.user.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public String register(RegistrationDtoRequest dto) {
        User user = userService.createUser(dto);
        String token = jwtService.generateToken(user);
        jwtService.saveToken(user, token);
        return token;
    }

    @Override
    public String login(LoginDtoRequest dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );
        User user = userService.findByUsername(dto.getUsername());
        List<Token> jwts = jwtService.findByUserAndNotExpired(user);
        if (jwts.isEmpty()) {
            String token = jwtService.generateToken(user);
            jwtService.saveToken(user, token);
            return token;
        }
        return jwts.stream().findFirst().get().getToken();
    }
}
