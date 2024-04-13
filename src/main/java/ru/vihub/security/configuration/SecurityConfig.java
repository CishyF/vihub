package ru.vihub.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import ru.vihub.security.Role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        http.securityMatcher("/admin*")
                        .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                                authorizationManagerRequestMatcherRegistry
                                        .requestMatchers(mvcMatcherBuilder.pattern("/admin*"))
                                        .hasRole(Role.ADMIN)
                        )
                        .formLogin(httpSecurityFormLoginConfigurer ->
                        httpSecurityFormLoginConfigurer
                                .loginPage("/login")
                                .loginProcessingUrl("/admin/login")
                                .failureUrl("/login?failure=true")
                )
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer.accessDeniedPage("/403"))
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
