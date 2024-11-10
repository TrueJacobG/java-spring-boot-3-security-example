package com.github.truejacobg.java_spring_boot_3_security_example.config;

import com.github.truejacobg.java_spring_boot_3_security_example.auth.AuthService;
import com.github.truejacobg.java_spring_boot_3_security_example.exception.GlobalException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static com.github.truejacobg.java_spring_boot_3_security_example.config.PermitAllEndpoints.PERMIT_ALL_ENDPOINTS;

@Slf4j
@Component
@AllArgsConstructor
public class TokenFilter extends OncePerRequestFilter {

    private final AuthService tokenService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return PERMIT_ALL_ENDPOINTS.stream().anyMatch(path::equals);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var tokenValue = request.getHeader("x-token");

        if (tokenValue == null || tokenValue.isEmpty()) {
            log.error("Cannot authenticate. Token value is null");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            var user = tokenService.getUserFromToken(tokenValue);
            var newSecurityContext = SecurityContextHolder.createEmptyContext();
            var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

            newSecurityContext.setAuthentication(new UsernamePasswordAuthenticationToken(user, null, authorities));
            SecurityContextHolder.setContext(newSecurityContext);

            filterChain.doFilter(request, response);
        } catch (GlobalException exception) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
        }
    }
}

