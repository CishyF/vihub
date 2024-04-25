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
    private final AuthenticationManager authenticationManager;

    @Override
    public void register(RegistrationDtoRequest dto) {
        userService.createUser(dto);
        //loadUserByUsername(dto.getUsername());
        //authenticateUser(dto.getUsername(), dto.getPassword());
    }

    @Override
    public void login(LoginDtoRequest dto) {
        //loadUserByUsername(dto.getUsername());
        //authenticateUser(dto.getUsername(), dto.getPassword());
        //User user = userService.findByUsername(dto.getUsername());
    }

//    private void authenticateUser(String username, String password){
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//    }
//    private void loadUserByUsername(String username){
//        userService.loadUserByUsername(username);
//    }
}
