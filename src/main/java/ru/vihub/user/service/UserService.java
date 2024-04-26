package ru.vihub.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.vihub.security.dto.RegistrationDtoRequest;
import ru.vihub.user.model.User;

import java.util.Optional;

public interface UserService{


    User findUserByUsername(String username);

    User createUser(RegistrationDtoRequest dto);

    UserDetailsService userDetailsService();
}
