package ru.vihub.security.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static ru.vihub.user.model.Role.ADMIN;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(request -> request
                            .requestMatchers("/stylesheet/**", "/images/**", "/auth/**", "/recommendations", "/home", "/watch/**", "/video/**", "/upload/**", "/js/**").permitAll()
                            .requestMatchers("/admin/**", "/subscriptions", "/profile").hasRole(ADMIN.name())
                            .anyRequest().authenticated()
                    )
                    .formLogin(form -> form.loginPage("/auth/login").permitAll().loginProcessingUrl("/auth/login")
                            .defaultSuccessUrl("/home", true))
                    .authenticationProvider(authenticationProvider)
                .build();
    }
}
