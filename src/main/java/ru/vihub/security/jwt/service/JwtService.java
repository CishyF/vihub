package ru.vihub.security.jwt.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.vihub.security.jwt.entity.Token;
import ru.vihub.user.model.User;

import java.util.List;
import java.util.Map;

public interface JwtService {

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateToken(UserDetails userDetails);

    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    List<Token> findByUserAndNotExpired(User user);

    Token saveToken(User user, String token);
}
