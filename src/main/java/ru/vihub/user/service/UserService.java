package ru.vihub.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.vihub.security.dto.RegistrationDtoRequest;
import ru.vihub.user.dto.UserDto;
import ru.vihub.user.model.User;

public interface UserService{
    User findByUsername(String username);
    UserDto findById(long id);
    User createUser(RegistrationDtoRequest dto);
    UserDto findCurrentUser();
    UserDetailsService userDetailsService();
    void updateUser(UserDto userDto) ;
}
