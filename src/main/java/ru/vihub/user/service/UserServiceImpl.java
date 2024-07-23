package ru.vihub.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vihub.security.dto.RegistrationDtoRequest;
import ru.vihub.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import ru.vihub.user.dto.UserDto;
import ru.vihub.user.model.User;
import ru.vihub.user.repository.UserRepository;


import java.util.ArrayList;


import static ru.vihub.user.mapper.UserMapper.mapToUser;

import static ru.vihub.user.model.Role.ADMIN;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
    }

    @Override
    public User createUser(RegistrationDtoRequest dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(ADMIN)
                .videoList(new ArrayList<>())
                .build();
        return userRepository.save(user);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::findUserByUsername;
    }


  @Override
  public User findById(long id) {
    return userRepository
        .findById(id)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format("There is no user with id=%d", id)));
  }


  @Override
  public void updateUser(UserDto userDto) {
    User user = mapToUser(userDto);
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User userPrevious = (User) principal;
    user.setId(userPrevious.getId());
    user.setRole(userPrevious.getRole());
    user.setPassword(userPrevious.getPassword());
    user.setPhotoUrl(userPrevious.getPhotoUrl());
    log.info("user{}", user);
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    ((User) principal).setUsername(user.getUsername());
    ((User) principal).setPassword(user.getPassword());
    Authentication newAuth =
        new UsernamePasswordAuthenticationToken(
            principal, auth.getCredentials(), auth.getAuthorities());
    userRepository.save(user);
    SecurityContextHolder.getContext().setAuthentication(newAuth);
  }

  @Override
  public boolean checkPassword(String password) {
    User user = findCurrentUser();
    return passwordEncoder.matches(password, user.getPassword());
  }

  @Override
  public void changeUserPassword(String newPassword) {
    User user = findCurrentUser();
    user.setPassword(passwordEncoder.encode(newPassword));
    userRepository.save(user);
  }

  @Override
  public User findCurrentUser() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return userRepository
        .findById(user.getId())
        .orElseThrow(
            () ->
                new EntityNotFoundException(
                    String.format("There is no user with id=%d", user.getId())));
  }
}
