package com.github.truejacobg.java_spring_boot_3_security_example.auth;

import com.github.truejacobg.java_spring_boot_3_security_example.auth.dto.LoginDto;
import com.github.truejacobg.java_spring_boot_3_security_example.auth.dto.TokenDto;
import com.github.truejacobg.java_spring_boot_3_security_example.auth.entity.Token;
import com.github.truejacobg.java_spring_boot_3_security_example.auth.exception.ExpiredTokenException;
import com.github.truejacobg.java_spring_boot_3_security_example.auth.exception.InvalidTokenException;
import com.github.truejacobg.java_spring_boot_3_security_example.user.UserService;
import com.github.truejacobg.java_spring_boot_3_security_example.user.dto.LoginUserResponseDto;
import com.github.truejacobg.java_spring_boot_3_security_example.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

import static com.github.truejacobg.java_spring_boot_3_security_example.auth.hashing.HashingUtil.generateRandomTokenValue;

@AllArgsConstructor
@Service
public class AuthService {

    private final UserService userService;

    private final TokenRepository tokenRepository;

    public TokenDto login(LoginDto loginDto) {
        var user = userService.findByEmail(loginDto.email(), loginDto.password());

        return createToken(user);
    }

    public LoginUserResponseDto getInfoFromToken(String tokenValue) {
        var token = tokenRepository.findByValue(tokenValue)
                .orElseThrow(() -> new InvalidTokenException("No token matches given token"));

        if (!isValid(token)) {
            throw new ExpiredTokenException("Token expired at [%s]".formatted(token.getExpireDate()));
        }

        return new LoginUserResponseDto(token.getUser().getEmail(), token.getUser().getRole());
    }

    public User getUserFromToken(String tokenValue) {
        var token = tokenRepository.findByValue(tokenValue)
                .orElseThrow(() -> new InvalidTokenException("Invalid token, token: [%s] is unknown for application".formatted(tokenValue)));

        if (!isValid(token)) {
            deleteToken(token);
            throw new ExpiredTokenException("Token expired at [%s]".formatted(token.getExpireDate()));
        }

        return token.getUser();
    }

    public TokenDto renewToken(TokenDto tokenDto) {
        var token = tokenRepository.findByValue(tokenDto.token())
                .orElseThrow(() -> new InvalidTokenException("Invalid token, token: [%s] is unknown for application".formatted(tokenDto.token())));

        if (token.isRemoved()) {
            throw new InvalidTokenException("Invalid token, your token has been removed!");
        }

        return createToken(token.getUser());
    }

    private TokenDto createToken(User user) {
        var tokenValue = generateRandomTokenValue();

        var token = Token.builder()
                .value(tokenValue)
                .user(user)
                .expireDate(Instant.now().plus(Duration.ofDays(1)))
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        tokenRepository.saveAndFlush(token);
        return new TokenDto(tokenValue);
    }

    private boolean isValid(Token token) {
        return !token.isRemoved() && token.getExpireDate().isAfter(Instant.now());
    }

    private void deleteToken(Token token) {
        token.setRemoved(true);
        tokenRepository.saveAndFlush(token);
    }
}
