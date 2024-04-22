package ru.vihub.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vihub.security.dto.RegistrationDtoRequest;
import ru.vihub.security.exception.EntityNotFoundException;
import ru.vihub.user.model.User;
import ru.vihub.user.repository.UserRepository;

import static ru.vihub.user.model.Role.ADMIN;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
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
                .build();
        return userRepository.save(user);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::findByUsername;
    }
}
