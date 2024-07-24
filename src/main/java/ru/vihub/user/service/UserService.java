package ru.vihub.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.vihub.security.dto.RegistrationDtoRequest;
import ru.vihub.user.dto.UserDto;
import ru.vihub.user.model.User;



public interface UserService{


  User findById(long id);


  User findUserByUsername(String username);

  User createUser(RegistrationDtoRequest dto);


  User findCurrentUser();

  UserDetailsService userDetailsService();

  void updateUser(UserDto userDto);

  boolean checkPassword(String password);

  void changeUserPassword(String newPassword);
}
