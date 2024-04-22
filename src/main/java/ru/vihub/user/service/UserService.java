package ru.vihub.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.vihub.security.dto.RegistrationDtoRequest;
import ru.vihub.user.model.User;

public interface UserService {

    User findByUsername(String username);

    User createUser(RegistrationDtoRequest dto);

    UserDetailsService userDetailsService();
}
