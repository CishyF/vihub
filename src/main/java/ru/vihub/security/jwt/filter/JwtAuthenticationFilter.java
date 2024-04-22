package ru.vihub.security.jwt.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.vihub.security.jwt.service.JwtService;
import ru.vihub.user.service.UserService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String HEADER_NAME = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().contains("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        String header = request.getHeader(HEADER_NAME);
        if (header == null || !header.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        String jwt = header.substring(BEARER_PREFIX.length());
        String username = jwtService.extractUsername(jwt);
        if (username != null && !username.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService
                    .userDetailsService()
                    .loadUserByUsername(username);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
