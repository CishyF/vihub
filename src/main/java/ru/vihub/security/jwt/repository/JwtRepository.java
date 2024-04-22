package ru.vihub.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vihub.security.jwt.entity.Token;
import ru.vihub.user.model.User;

import java.util.List;

@Repository
public interface JwtRepository extends JpaRepository<Token, Long> {

    List<Token> findByUserAndExpired(User user, boolean expired);
}
