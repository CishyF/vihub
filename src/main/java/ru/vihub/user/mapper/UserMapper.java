package ru.vihub.user.mapper;

import ru.vihub.user.dto.UserDto;
import ru.vihub.user.model.User;

public class UserMapper {
  public static User mapToUser(UserDto userDto) {
    return User.builder()
        .id(userDto.getId())
        .username(userDto.getUsername())
        .password(userDto.getPassword())
        .email(userDto.getEmail())
        .firstname(userDto.getFirstname())
        .lastname(userDto.getLastname())
        .phone(userDto.getPhone())
        .address(userDto.getAddress())
        .github(userDto.getGithub())
        .instagram(userDto.getInstagram())
        .telegram(userDto.getTelegram())
        .photoUrl(userDto.getPhotoUrl())
        .build();
  }

  public static UserDto mapToUserDto(User user) {
    return UserDto.builder()
        .id(user.getId())
        .username(user.getUsername())
        .password(user.getPassword())
        .email(user.getEmail())
        .firstname(user.getFirstname())
        .lastname(user.getLastname())
        .phone(user.getPhone())
        .address(user.getAddress())
        .github(user.getGithub())
        .instagram(user.getInstagram())
        .telegram(user.getTelegram())
        .photoUrl(user.getPhotoUrl())
        .build();
  }
}
