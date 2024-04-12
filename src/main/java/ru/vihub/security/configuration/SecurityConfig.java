package ru.vihub.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.vihub.security.Role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        List<UserDetails> userDetails = new ArrayList<>();
        userDetails.add(
                User.builder()
                        .username("cishy")
                        .password(passwordEncoder.encode("root"))
                        .authorities(Collections.singletonList(new SimpleGrantedAuthority(Role.ADMIN)))
                        .build()
        );
        userDetails.add(
                User.builder()
                        .username("hairloo")
                        .password(passwordEncoder.encode("root"))
                        .authorities(Collections.singletonList(new SimpleGrantedAuthority(Role.ADMIN)))
                        .build()
        );
        userDetails.add(
                User.builder()
                        .username("ScarFace163")
                        .password(passwordEncoder.encode("root"))
                        .authorities(Collections.singletonList(new SimpleGrantedAuthority(Role.ADMIN)))
                        .build()
        );
        userDetails.add(
                User.builder()
                        .username("Ramil154")
                        .password(passwordEncoder.encode("root"))
                        .authorities(Collections.singletonList(new SimpleGrantedAuthority(Role.ADMIN)))
                        .build()
        );
        return new InMemoryUserDetailsManager(userDetails);
    }
}
